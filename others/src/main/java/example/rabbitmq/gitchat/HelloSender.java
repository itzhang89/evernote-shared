package example.rabbitmq.gitchat;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class HelloSender {
  @Autowired
  private AmqpTemplate rabbitTemplate;

  public void send() {
    String context = "hello " + ZonedDateTime.now();
    System.out.println("Sender : " + context);
    this.rabbitTemplate.convertAndSend("hello", context);
  }
}
