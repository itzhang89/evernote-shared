package example.rabbitmq.gitchat.spring;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("gitchat")
@Configuration
public class RabbitConfig {

  @Bean
  public Queue Queue() {
    return new Queue("hello");
  }
}
