package top.ilovestudy.yinxiang.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDto {
  private String id;
  private String title;
  private String avatar;
  private String author;
  private String content;
  private String url;
  private String briefIntro;
  private String created;
  private String updated;
  private String sourceUrl;
  private String category;
  private int commentNum;
}
