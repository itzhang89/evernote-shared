package top.ilovestudy.yinxiang.model.mapper;

import org.junit.jupiter.api.Test;
import top.ilovestudy.yinxiang.model.entites.Label;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LabelMapperTest {

  @Test
  void shouldConvertGuidAndNameToTag() {
    Label Label = LabelMapper.INSTANCE.convertGuidAndNameToLabel("guid_number", "guid_name");
    assertEquals("guid_number", Label.getId());
    assertEquals("guid_name", Label.getName());
  }
}