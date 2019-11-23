package top.ilovestudy.yinxiang.model.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.ZonedDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;
import static javax.persistence.CascadeType.ALL;
import static top.ilovestudy.yinxiang.utils.DateUtils.DATE_TIME_FORMAT;

@Data
@NoArgsConstructor
@Entity(name = "article")
public class Article {

  @Id
  @Column(unique = true, nullable = false)
  @GeneratedValue(generator = "custom-uuid")
  @GenericGenerator(name = "custom-uuid", strategy = "top.ilovestudy.yinxiang.config.CustomUUIDGenerator")
  private String id;
  private String title;
  @Lob
  private String content;
  private String author;
  private String briefIntro;
  @JsonFormat(shape = Shape.STRING, pattern = DATE_TIME_FORMAT, timezone = "UTC")
  private ZonedDateTime created;
  @JsonFormat(shape = Shape.STRING, pattern = DATE_TIME_FORMAT, timezone = "UTC")
  private ZonedDateTime updated;
  @JsonFormat(shape = Shape.STRING, pattern = DATE_TIME_FORMAT, timezone = "UTC")
  private ZonedDateTime deleted;
  private boolean isShared;
  private boolean isOriginal;
  private String sourceUrl;
  private int updateSequenceNum;

  @OneToMany(cascade = ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "article_id")
  private List<Label> labelGuidList;

  @OneToOne(cascade = ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Category category;

}
