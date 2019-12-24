package example.springcode.chap03.lookup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext bf = new ClassPathXmlApplicationContext("example.springcode.chap03.lookup/MyBeanTest.xml");
    GetBeanTest getBeanTest = (GetBeanTest) bf.getBean("getBeanTest");
    getBeanTest.showMe();
  }
}
