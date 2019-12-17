package example.rabbitmq;

import example.rabbitmq.gitchat.spring.HelloSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitMqHelloTest {

  @Autowired
  private HelloSender helloSender;

  @Test
  public void hello() throws Exception {
    helloSender.send();
    Thread.sleep(1000l);
  }
}