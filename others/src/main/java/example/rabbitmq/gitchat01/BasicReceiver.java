package example.rabbitmq.gitchat01;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

import static example.rabbitmq.gitchat01.MqManager.TASK_QUEUE_NAME;

public class BasicReceiver {

  public static void main(String[] argv) throws Exception {
    Connection connection = MqManager.createConnection();
    Channel channel = connection.createChannel();

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    // consumer 简单的将接收到的消息打印出来
    channel.basicConsume(TASK_QUEUE_NAME, true, new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    });
  }
}