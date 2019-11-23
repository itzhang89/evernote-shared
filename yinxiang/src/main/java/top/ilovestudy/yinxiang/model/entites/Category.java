package top.ilovestudy.yinxiang.model.entites;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Category {
  @Id
  @Column(unique = true, nullable = false)
  @GeneratedValue(generator = "custom-uuid")
  @GenericGenerator(name = "custom-uuid", strategy = "top.ilovestudy.yinxiang.config.CustomUUIDGenerator")
  private String id;
  private String name;
}
