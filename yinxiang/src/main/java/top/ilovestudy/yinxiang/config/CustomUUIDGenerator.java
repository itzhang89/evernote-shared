package top.ilovestudy.yinxiang.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class CustomUUIDGenerator extends UUIDGenerator {

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
    try {
      Method getIdMethod = object.getClass().getMethod("getId");
      Object id = getIdMethod.invoke(object);
      if (id != null) {
        return (Serializable) id;
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return super.generate(session, object);
  }
}