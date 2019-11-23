package top.ilovestudy.yinxiang.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto implements Comparable {
  private String id;
  private String name;
  private int count;
  private String url;

  public CategoryDto(String id, String name, int count) {
    this.id = id;
    this.name = name;
    this.count = count;
  }

  @Override
  public int compareTo(Object o) {
    CategoryDto categoryDto = (CategoryDto) o;
    if (this.count > categoryDto.getCount()) {
      return -1;
    } else if (this.count == categoryDto.getCount()) {
      return 0;
    }
    return 1;
  }
}
