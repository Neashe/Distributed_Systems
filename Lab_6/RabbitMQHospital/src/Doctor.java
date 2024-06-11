import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

// nasz producent
public class Doctor {
    private final static String EXCHANGE_NAME = "exchange_hospital";
    private final static String LOG_QUEUE_NAME = "log_queue";
    private final static String BROADCAST_EXCHANGE_NAME = "broadcast_exchange";


    public static void main(String[] args) throws Exception {
        // info
        System.out.println("DOCTOR");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // queues
        List<String> validProblems = Arrays.asList("knee","hip","elbow");
        for (String problem : validProblems){
            channel.queueDeclare(problem, false, false, false, null);
        }
        channel.queueDeclare(LOG_QUEUE_NAME, false, false, false, null);
        channel.exchangeDeclare(BROADCAST_EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String broadcastQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(broadcastQueueName, BROADCAST_EXCHANGE_NAME, "");

        // Listen for broadcast messages
        channel.basicConsume(broadcastQueueName, true, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[ADMIN MESSAGE]: " + message);
        }, consumerTag -> {});

        while (true){
            // problem type
            System.out.println("Enter problem (knee, hip, elbow): ");
            String problem = br.readLine().trim();

            // validate data
            if (!validProblems.contains(problem)){
                System.out.println("We don't solve those type of problems in our hospital...");
                continue;
            }

            // patient data
            System.out.print("Enter person name: ");
            String message = br.readLine();

            // break condition
            if("exit".equals(message)){
                break;
            }

            String correlationId = UUID.randomUUID().toString();
            String replyQueueName = channel.queueDeclare().getQueue();

            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(correlationId)
                    .replyTo(replyQueueName)
                    .build();

            channel.basicPublish(EXCHANGE_NAME, problem, props, message.getBytes("UTF-8"));
            System.out.println("Message sent: " + message);

            String logMessage = "DOCTOR sent: " + message;
            channel.basicPublish("", LOG_QUEUE_NAME, null, logMessage.getBytes("UTF-8"));
            CompletableFuture<String> response = new CompletableFuture<>();

            String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
                if (delivery.getProperties().getCorrelationId().equals(correlationId)) {
                    response.complete(new String(delivery.getBody(), "UTF-8"));
                }
            }, consumerTag -> {});
            response.thenAccept(res -> {
                System.out.println("[TECHNICIAN]: "+ res);
            });
        }
        channel.close();
        connection.close();
    }

}