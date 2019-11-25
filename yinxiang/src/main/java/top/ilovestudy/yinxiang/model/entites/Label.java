package top.ilovestudy.yinxiang.model.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Label)) return false;
    Label label = (Label) o;
    return Objects.equals(getTagGuid(), label.getTagGuid()) &&
        Objects.equals(getName(), label.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTagGuid(), getName());
  }
}
