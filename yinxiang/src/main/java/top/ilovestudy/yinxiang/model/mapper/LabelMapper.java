package top.ilovestudy.yinxiang.model.mapper;

import com.evernote.edam.type.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.ilovestudy.yinxiang.model.LabelDto;
import top.ilovestudy.yinxiang.model.entites.Label;

import java.util.List;

@Mapper
public interface LabelMapper {

  LabelMapper INSTANCE = Mappers.getMapper(LabelMapper.class);

  @Mapping(source = "id", target = "url")
  LabelDto labelToLabelDto(Label label);

  @Mapping(source = "guid", target = "id")
  Label convertGuidAndNameToLabel(String guid, String name);

  @Mapping(source = "guid", target = "id")
  Label convertTagGuidToLabel(String guid);

  @Mapping(source = "guid", target = "id")
  Label tagToLabel(Tag tag);

  List<Label> convertTagGuidsToLabelList(List<String> tagGuids);
}
