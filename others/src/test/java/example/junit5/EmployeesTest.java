package example.junit5;

import example.junit5.entity.Employee;
import example.junit5.entity.EmployeeJdbcDao;
import example.junit5.extpoint.EmployeeDaoParameterResolver;
import example.junit5.extpoint.EmployeeDatabaseSetupExtension;
import example.junit5.extpoint.EnvironmentExtension;
import example.junit5.extpoint.IgnoreFileNotFoundExceptionExtension;
import example.junit5.extpoint.LoggingExtension;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({ EnvironmentExtension.class,
    EmployeeDatabaseSetupExtension.class, EmployeeDaoParameterResolver.class })
@ExtendWith(LoggingExtension.class)
@ExtendWith(IgnoreFileNotFoundExceptionExtension.class)
public class EmployeesTest {
  private EmployeeJdbcDao employeeDao;
  private Logger logger;

  public EmployeesTest(EmployeeJdbcDao employeeDao) {
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