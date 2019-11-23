package example.junit5.extpoint;

import example.junit5.entity.EmployeeJdbcDao;
import example.junit5.utils.JdbcConnectionUtil;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class EmployeeDatabaseSetupExtension implements
    BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
  private EmployeeJdbcDao employeeDao;

  private Connection con;
  private Savepoint savepoint;

  public EmployeeDatabaseSetupExtension(String url, String driver, String username, String password) {
    con = JdbcConnectionUtil.getConnection(url, driver, username, password);
    employeeDao = new EmployeeJdbcDao(con);
  }

  @Override
  public void afterAll(ExtensionContext context) throws SQLException {
    if (con != null) {
      con.close();
    }
  }

  @Override
  public void afterEach(ExtensionContext context) throws SQLException {
    con.rollback(savepoint);
  }

  @Override
  public void beforeAll(ExtensionContext context) throws SQLException {
    employeeDao.createTable();
  }

  @Override
  public void beforeEach(ExtensionContext context) throws SQLException {
    con.setAutoCommit(false);
    savepoint = con.setSavepoint("before");
  }
}