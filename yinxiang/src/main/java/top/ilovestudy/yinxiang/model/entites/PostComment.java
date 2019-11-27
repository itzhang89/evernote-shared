package top.ilovestudy.yinxiang.model.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.ZonedDateTime;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
public class PostComment {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne(cascade = ALL)
  @JoinColumn(name = "parent_id")
  private PostComment parentPostComment;
  private Long belongId;

  private String username;
  private ZonedDateTime commentTime;

  @Column(columnDefinition = "text")
  private String content;
  private Long likes;
  private Long disLikes;

}
