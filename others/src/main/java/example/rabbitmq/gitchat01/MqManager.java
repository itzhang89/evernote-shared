package example.rabbitmq.gitchat01;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqManager {
  static final String TASK_QUEUE_NAME = "hello";
  static final String EXCHANGE_NAME = "logs";

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
