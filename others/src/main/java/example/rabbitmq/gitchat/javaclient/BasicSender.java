package example.rabbitmq.gitchat.javaclient;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.util.concurrent.TimeoutException;

import static example.rabbitmq.gitchat.javaclient.MqManager.EXCHANGE_NAME;

public class BasicSender {

  public static void main(String[] argv) throws java.io.IOException, TimeoutException {

    Connection connection = MqManager.createConnection();
    // 创建信道道
    Channel channel = connection.createChannel();
    // 通过信道声明队列
    AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(MqManager.TASK_QUEUE_NAME, true, false, false, null);
    // 声明交换机
    AMQP.Exchange.DeclareOk fanout = channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    // 将队列绑定上交换机。
    AMQP.Queue.BindOk bindOk = channel.queueBind(MqManager.TASK_QUEUE_NAME, EXCHANGE_NAME, "");
    String message = "test xiao ming";
    // 生产者通过发送消息至 RabbitMQ
    channel.basicPublish(EXCHANGE_NAME, MqManager.TASK_QUEUE_NAME, MessageProperties.BASIC, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    // 通道关闭
    channel.close();
    connection.close();
  }
}