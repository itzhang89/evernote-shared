package top.ilovestudy.yinxiang.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.ilovestudy.yinxiang.model.LabelDto;
import top.ilovestudy.yinxiang.model.entites.Label;

import java.util.List;

@Mapper
public interface LabelMapper {

  LabelMapper INSTANCE = Mappers.getMapper(LabelMapper.class);

  @Mapping(target = "id", source = "tagGuid")
  @Mapping(target = "url", source = "tagGuid")
  LabelDto labelToLabelDto(Label label);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", ignore = true)
  @Mapping(target = "tagGuid", source = "guid")
  Label convertTagGuidToLabel(String guid);

  List<Label> convertTagGuidsToLabelList(List<String> tagGuids);
}
