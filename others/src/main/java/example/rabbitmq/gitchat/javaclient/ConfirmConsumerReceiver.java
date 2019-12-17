package example.rabbitmq.gitchat.javaclient;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConfirmConsumerReceiver {

  public static void main(String[] args) throws IOException, TimeoutException {
    Connection conn = MqManager.createConnection();
    Channel channel = conn.createChannel();

    // 消费消息
    channel.basicConsume(MqManager.TASK_QUEUE_NAME, false, "myConsumer Tag", new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String routingKey = envelope.getRoutingKey();
        String contentType = properties.getContentType();
        long deliveryTag = envelope.getDeliveryTag();
        System.out.println("routingKey:" + routingKey + ",contentType:" + contentType + ",deliveryTag:" + deliveryTag + ",Msg body:" + new String(body));
        channel.basicAck(deliveryTag, false);
      }
    });
  }
}