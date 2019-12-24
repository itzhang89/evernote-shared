package example.springcode.chap03.replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class TestMethodReplacer implements MethodReplacer {
  @Override
  public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
    System.out.println("我已经替换了方法");
    return null;
  }
}
