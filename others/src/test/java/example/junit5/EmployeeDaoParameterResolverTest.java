package example.junit5;


import example.junit5.entity.EmployeeJdbcDao;
import example.junit5.extpoint.EmployeeDaoParameterResolver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({EmployeeDaoParameterResolver.class})
public class EmployeeDaoParameterResolverTest {

  public EmployeeDaoParameterResolverTest(EmployeeJdbcDao employeeJdbcDao) {
    System.out.println("constructor employeeJdbcDao");
  }

  @BeforeAll
  static void setUpAll() {
    System.out.println(EmployeeDaoParameterResolverTest.class.getSimpleName() + ": I am in before All");
  }

  @AfterAll
  static void tearDownAll() {
    System.out.println(EmployeeDaoParameterResolverTest.class.getSimpleName() + ": I am in After All");
  }

  @BeforeEach
  void setUp() {
    System.out.println(EmployeeDaoParameterResolverTest.class.getSimpleName() + ": I am in before Each");
  }

  @Test
  void shouldEmployeeDaoParameterResolverTest1(EmployeeJdbcDao employeeJdbcDao) {
    System.out.println("running the shouldEmployeeDaoParameterResolverTest1: " + employeeJdbcDao.getConName());
  }

  @Test
  void shouldEmployeeDaoParameterResolverTest2() {
    System.out.println("running the shouldEmployeeDaoParameterResolverTest2");
  }

  @AfterEach
  void tearDown() {
    System.out.println(EmployeeDaoParameterResolverTest.class.getSimpleName() + ": I am in After Each");
  }

}
