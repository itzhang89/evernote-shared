package example.junit5.extpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * 该方法会在 BeforeAll 和 BeforeEach 之间时间段调用。放在类上面就是对所有的测试生效，放在方法上就是对单个方法有效
 */
public class LoggingExtension implements TestInstancePostProcessor {

  @Override
  public void postProcessTestInstance(Object testInstance,
                                      ExtensionContext context) throws Exception {
    Logger logger = LogManager.getLogger(testInstance.getClass());
    testInstance.getClass()
        .getMethod("setLogger", Logger.class)
        .invoke(testInstance, logger);

    System.err.println("invoke the LoggingExtension@postProcessTestInstance method");
  }
}
