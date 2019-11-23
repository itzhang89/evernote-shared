package top.ilovestudy.yinxiang.model;

import lombok.Data;

@Data(staticConstructor = "of")
public class ArchiveDto implements Comparable {
  private final int count;
  private final String date;
  private String url;

  @Override
  public int compareTo(Object that) {
    ArchiveDto archiveDto = (ArchiveDto) that;
    String date = archiveDto.getDate();
    return this.date.compareTo(date);
  }
}
