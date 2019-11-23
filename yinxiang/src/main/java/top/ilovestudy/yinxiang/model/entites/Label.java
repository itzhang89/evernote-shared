package top.ilovestudy.yinxiang.model.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Label {
  @Id
  @Column(unique = true, nullable = false)
  @GeneratedValue(generator = "custom-uuid")
  @GenericGenerator(name = "custom-uuid", strategy = "top.ilovestudy.yinxiang.config.CustomUUIDGenerator")
  private String id;
  private String name;

  public Label(String id) {
    this.id = id;
  }
}
