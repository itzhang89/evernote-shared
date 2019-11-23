package top.ilovestudy.yinxiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class EverNoteShareApplication {
  public static void main(String[] args) {
    SpringApplication.run(EverNoteShareApplication.class, args);
  }

  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
