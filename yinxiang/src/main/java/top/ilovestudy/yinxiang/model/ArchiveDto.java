package top.ilovestudy.yinxiang.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArchiveDto {
  private String date;
  private int number;
  private String url;
}
