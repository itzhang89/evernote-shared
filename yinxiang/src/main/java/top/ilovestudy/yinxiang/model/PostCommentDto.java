package top.ilovestudy.yinxiang.model;

import lombok.Data;

import java.util.List;

@Data
public class PostCommentDto {

  private Long id;
  private String username;
  private String replyTo;
  private String commentTime;
  private String content;
  private Long likes;
  private Long disLikes;
  private List<PostCommentDto> childList;

}
