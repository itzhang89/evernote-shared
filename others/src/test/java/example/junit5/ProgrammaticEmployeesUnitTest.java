package example.junit5;

import example.junit5.entity.Employee;
import example.junit5.entity.EmployeeJdbcDao;
import example.junit5.extpoint.EmployeeDaoParameterResolver;
import example.junit5.extpoint.EmployeeDatabaseSetupExtension;
import example.junit5.extpoint.EnvironmentExtension;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>通过RegisterExtension注解，可以对插件进行一些参数的注入，通常会定义为静态方法，对每一个test方法都有效</p>
 */
@ExtendWith({EnvironmentExtension.class, EmployeeDaoParameterResolver.class})
public class ProgrammaticEmployeesUnitTest {

  @RegisterExtension
  static EmployeeDatabaseSetupExtension DB =
      new EmployeeDatabaseSetupExtension("jdbc:h2:mem:AnotherDb;DB_CLOSE_DELAY=-1", "org.h2.Driver", "sa", "");
  private static Connection con;

  private EmployeeJdbcDao employeeDao;

  private Logger logger;

  public ProgrammaticEmployeesUnitTest(EmployeeJdbcDao employeeDao) {
    this.employeeDao = employeeDao;
  }

  @Test
  public void whenAddEmployee_thenGetEmployee() throws SQLException {
    Employee emp = new Employee(1, "john");
    employeeDao.add(emp);
    assertEquals(1, employeeDao.findAll().size());
  }

  @Test
  public void whenGetEmployees_thenEmptyList() throws SQLException {
    assertEquals(0, employeeDao.findAll().size());
  }

  public void setLogger(Logger logger) {
    this.logger = logger;
  }
}
