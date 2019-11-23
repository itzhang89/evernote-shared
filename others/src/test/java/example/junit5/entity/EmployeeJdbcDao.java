package example.junit5.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeJdbcDao {
  private Connection con;
  private String conName;

  public EmployeeJdbcDao(Connection con) {
    this.con = con;
  }

  public void createTable() throws SQLException {
    // create employees table
  }

  public void add(Employee emp) throws SQLException {
    // add employee record
  }

  public List<Employee> findAll() throws SQLException {
    // query all employee records
    return new ArrayList<>();
  }
}
