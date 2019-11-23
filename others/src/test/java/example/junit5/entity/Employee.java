package example.junit5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
  private long id;
  private String firstName;
}
