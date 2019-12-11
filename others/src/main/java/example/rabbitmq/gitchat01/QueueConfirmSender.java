package example.rabbitmq.gitchat01;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.utils.SerializationUtils;

import java.io.Serializable;

import static example.rabbitmq.gitchat01.MqManager.CONFIRM_LISTENER;
import static example.rabbitmq.gitchat01.MqManager.EXCHANGE_NAME;
import static example.rabbitmq.gitchat01.MqManager.TASK_QUEUE_NAME;

/**
 * 应答模式之confirm机制：消息发送
 *
 * @author xiaoming
 */
public class QueueConfirmSender {

  /**
   * confirm机制：确认publisher发送消息到broker，由broker进行应答(不能确认是否被有效消费)
   * confirmSelect，进入confirm消息确认模式
   * ，确认方式：
   * 1、异步ConfirmListener；
   * 2、同步waitForConfirms
   * ConfirmListener、waitForConfirms均需要配合confirm机制使用
   *
   * @param mes
   * @throws Exception
   */
  public static void txSend(Serializable mes) throws Exception {
    Connection conn = MqManager.createConnection();
    Channel channel = conn.createChannel();
    channel.queueDeclare(EXCHANGE_NAME, true, false, false, null);
    // 开启transaction机制
    channel.confirmSelect();
    channel.exchangeDeclare(TASK_QUEUE_NAME, "direct");
    channel.queueBind(EXCHANGE_NAME, TASK_QUEUE_NAME, "routingKey");
    // 异步实现发送消息的确认(此部分的消息确认是指发送消息到队列，并非确认消息的有效消费)
    channel.addConfirmListener(CONFIRM_LISTENER);
    channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
      System.err.println("---------handle  return----------");
      System.err.println("replyCode: " + replyCode);
      System.err.println("replyText: " + replyText);
      System.err.println("exchange: " + exchange);
      System.err.println("routingKey: " + routingKey);
      System.err.println("properties: " + properties);
      System.err.println("body: " + new String(body));
    });
    for (int i = 0; i < 1; i++) {
      System.out.println("---------消息发送-----");
      // mandatory=true
      channel.basicPublish(TASK_QUEUE_NAME, "routingkeyError", true, MessageProperties.PERSISTENT_BASIC, SerializationUtils.serialize(mes.toString() + i));
    }
    Thread.sleep(1000);
    channel.close();
    conn.close();
  }

  public static void main(String[] args) throws Exception {
    txSend("hello world!");
  }
}