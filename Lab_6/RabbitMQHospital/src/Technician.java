import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Technician {
    private static final String EXCHANGE_NAME = "exchange_hospital";
    private final static String LOG_QUEUE_NAME = "log_queue";
    private final static String BROADCAST_EXCHANGE_NAME = "broadcast_exchange";

    public static void main(String[] args) throws Exception {
        System.out.println("TECHNICIAN");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);

        // queues
        List<String> validProblems = Arrays.asList("knee","hip","elbow");
        for (String problem : validProblems){
            channel.queueDeclare(problem, false, false, false, null);
        }
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(LOG_QUEUE_NAME, false, false, false, null);
        channel.exchangeDeclare(BROADCAST_EXCHANGE_NAME,BuiltinExchangeType.FANOUT);

        // Specialization
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter specializations: ");
        String specializations = br.readLine();
        String[] specializationArray = specializations.split(" ");

        // Bind
        for (String spec : specializationArray){
            channel.queueBind(spec, EXCHANGE_NAME, spec);
        }

        String broadcastQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(broadcastQueueName, BROADCAST_EXCHANGE_NAME, "");

        // Listen for broadcast messages
        channel.basicConsume(broadcastQueueName, true, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[ADMIN MESSAGE]: " + message);
        }, consumerTag -> {});

        // message handling
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);

                String response = message + " " + envelope.getRoutingKey() + " done";

                int timeToSleep = 5;
                try {
                    Thread.sleep(timeToSleep*1000);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();
                channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                channel.basicAck(envelope.getDeliveryTag(), false);

                String logMessage = "TECHNICIAN handled: " + message;
                channel.basicPublish("", LOG_QUEUE_NAME, null, logMessage.getBytes("UTF-8"));
            }
        };

        // start listening
        System.out.println("Waiting for messages...");
        for (String spec : specializationArray){
            channel.basicConsume(spec,false,consumer);
        }
    }
}
