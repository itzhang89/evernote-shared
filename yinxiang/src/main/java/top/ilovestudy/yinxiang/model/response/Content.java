package top.ilovestudy.yinxiang.model.response;

import lombok.Data;

@Data
public class Content {
  private int status;
  private String title;
  private String html;
  private String summary;
}
