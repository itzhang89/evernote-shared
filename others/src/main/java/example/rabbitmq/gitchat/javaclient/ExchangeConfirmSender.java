package example.rabbitmq.gitchat.javaclient;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.utils.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class ExchangeConfirmSender {

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
    channel.queueDeclare(MqManager.TASK_QUEUE_NAME, true, false, false, null);

    // 开启confirm机制
    channel.confirmSelect();
    // 异步实现发送消息的确认(此部分的消息确认是指发送消息到队列，并非确认消息的有效消费)
    channel.addConfirmListener(new ConfirmListener() {

      @Override
      public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        // multiple：测试发现multiple随机true或false，原因未知
        System.out.println("Nack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
      }

      @Override
      public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("Ack deliveryTag:" + deliveryTag + ",multiple:" + multiple);
      }
    });
    for (int i = 0; i < 10; i++) {
      System.out.println("---------消息发送-----");
      channel.basicPublish(MqManager.EXCHANGE_NAME, MqManager.TASK_QUEUE_NAME, null, SerializationUtils.serialize(mes.toString() + i));
    }
    //同步实现发送消息的确认
    channel.waitForConfirms();
    System.out.println("-----end of sender------");
    channel.close();
    conn.close();
  }

  public static void main(String[] args) throws Exception {
    txSend("hello world!");
  }
}
