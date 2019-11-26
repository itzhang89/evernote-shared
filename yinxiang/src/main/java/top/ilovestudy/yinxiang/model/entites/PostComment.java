package top.ilovestudy.yinxiang.model.entites;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
public class PostComment {

  @Id
  @GeneratedValue
  private Long id;

  //  @OneToOne(cascade = ALL)
//  @JoinColumn(name = "child_id")
//  private PostComment childId;
  private Long childId;

  private String username;
  private ZonedDateTime commentTime;

  @Column(columnDefinition = "text")
  private String content;
  private Long likes;
  private Long disLikes;

}
