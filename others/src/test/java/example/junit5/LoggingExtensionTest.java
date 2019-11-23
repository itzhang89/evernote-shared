package example.junit5;


import example.junit5.extpoint.LoggingExtension;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class LoggingExtensionTest {

  @BeforeAll
  static void setUpAll() {
    System.out.println(LoggingExtensionTest.class.getSimpleName() + ": I am in before All");
  }

  @AfterAll
  static void tearDownAll() {
    System.out.println(LoggingExtensionTest.class.getSimpleName() + ": I am in After All");
  }

  @BeforeEach
  void setUp() {
    System.out.println(LoggingExtensionTest.class.getSimpleName() + ": I am in before Each");
  }

  @Test
  @ExtendWith({LoggingExtension.class})
  void shouldLoggingExtensionTest1() {
    System.out.println("running the shouldLoggingExtensionTest1");
  }

  @Test
  void shouldLoggingExtensionTest2() {
    System.out.println("running the shouldLoggingExtensionTest2");
  }

  @AfterEach
  void tearDown() {
    System.out.println(LoggingExtensionTest.class.getSimpleName() + ": I am in After Each");
  }

  public void setLogger(Logger logger) {
    System.err.println("running the setLogger method...");
  }
}
