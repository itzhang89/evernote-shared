package top.ilovestudy.yinxiang.model;

public enum ElementDto {
  EN_NOTE("en-note"),

  EN_MEDIA("en-media"),

  EN_CRYPT("en-crypt"),

  EN_TODO("en-todo");

  private String value;

  ElementDto(String value) {
    this.value = value;
  }
}
