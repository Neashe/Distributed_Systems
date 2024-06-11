import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Admin {
    private final static String LOG_QUEUE_NAME = "log_queue";
    private final static String BROADCAST_EXCHANGE_NAME = "broadcast_exchange";

    public static void main(String[] args) throws Exception {
        System.out.println("ADMINISTRATOR");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // queues
        channel.queueDeclare(LOG_QUEUE_NAME, false, false, false, null);
        channel.exchangeDeclare(BROADCAST_EXCHANGE_NAME,BuiltinExchangeType.FANOUT);

        // Consume log messages
        channel.basicConsume(LOG_QUEUE_NAME, true, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[LOG]: " + message);
        }, consumerTag -> {});

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter message to broadcast (or 'exit' to quit): ");
            String message = br.readLine();

            if ("exit".equals(message)) {
                break;
            }

            channel.basicPublish(BROADCAST_EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println("Broadcast message sent: " + message);
        }

        channel.close();
        connection.close();
    }
}
