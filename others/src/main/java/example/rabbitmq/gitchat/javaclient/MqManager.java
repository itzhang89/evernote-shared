package example.rabbitmq.gitchat.javaclient;

import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqManager {
  static final String TASK_QUEUE_NAME = "hello";
  static final String EXCHANGE_NAME = "logs";
  static final ConfirmListener CONFIRM_LISTENER = new ConfirmListener() {

    @Override
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {
      // multiple：测试发现multiple随机true或false，原因未知
      System.out.println("Nack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
    }

    @Override
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
      System.out.println("Ack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
    }
  };

  static Connection createConnection() throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    factory.setPort(5672);
    factory.setUsername("admin");
    factory.setPassword("admin");
    factory.setVirtualHost("/");
    // 创建连接
    return factory.newConnection();
  }
}
