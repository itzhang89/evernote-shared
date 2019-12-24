package example.springcode.chap03.replace;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext bf = new ClassPathXmlApplicationContext("example.springcode.chap03/change/replaceBeanTest.xml");
    TestChangeMethod testChangeMethod = (TestChangeMethod) bf.getBean("testChangeMethod");
    testChangeMethod.changeMe();
  }
}
