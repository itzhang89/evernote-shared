package example.junit5;


import example.junit5.extpoint.EnvironmentExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({EnvironmentExtension.class})
public class EnvironmentExtensionTest {

  @BeforeAll
  static void setUpAll() {
    System.out.println(EnvironmentExtensionTest.class.getSimpleName() + ": I am in before All");
  }

  @AfterAll
  static void tearDownAll() {
    System.out.println(EnvironmentExtensionTest.class.getSimpleName() + ": I am in After All");
  }

  @BeforeEach
  void setUp() {
    System.out.println(EnvironmentExtensionTest.class.getSimpleName() + ": I am in before Each");
  }

  @Test
  void shouldEnvironmentExtensionTest1() {
    System.out.println("running the shouldEnvironmentExtensionTest1");
  }

  @Test
  void shouldEnvironmentExtensionTest2() {
    System.out.println("running the shouldEnvironmentExtensionTest2");
  }

  @AfterEach
  void tearDown() {
    System.out.println(EnvironmentExtensionTest.class.getSimpleName() + ": I am in After Each");
  }

}
