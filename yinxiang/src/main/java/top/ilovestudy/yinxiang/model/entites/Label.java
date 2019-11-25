package top.ilovestudy.yinxiang.model.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @GeneratedValue
  @Column(updatable = false, nullable = false)
  private Long id;
  private String tagGuid;
  private String name;

//  @Column(name = "article_id", nullable = false)
//  private String articleId;
}
