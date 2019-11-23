package example.junit5.utils;

import lombok.NoArgsConstructor;

import java.sql.Connection;

@NoArgsConstructor
public class JdbcConnectionUtil {

  private static Connection con;

  public static Connection getConnection(String url, String driver, String username, String password) {
    if (con == null) {
      // create connection
      return con;
    }
    return con;
  }
}
