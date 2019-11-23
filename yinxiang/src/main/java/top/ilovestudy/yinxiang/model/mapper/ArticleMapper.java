package top.ilovestudy.yinxiang.model.mapper;

import com.evernote.edam.type.Note;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import top.ilovestudy.yinxiang.model.ArticleDto;
import top.ilovestudy.yinxiang.model.entites.Article;
import top.ilovestudy.yinxiang.model.entites.Label;
import top.ilovestudy.yinxiang.model.mapper.annotion.StringToDateTime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Mapper(uses = LabelMapper.class)
public interface ArticleMapper {
  ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
  String HTTPS_APP_YINXIANG_COM_FX = "https://app.yinxiang.com/fx/";

  @Mapping(target = "avatar", constant = "/images/image_1.jpg")
  @Mapping(source = "id", target = "url", qualifiedByName = {"convertGuidToUrl"})
  @Mapping(source = "created", target = "created", qualifiedByName = {"convertZoneDateTimeToString"})
  @Mapping(source = "updated", target = "updated", qualifiedByName = {"convertZoneDateTimeToString"})
  @Mapping(target = "commentNum", constant = "0")
  @Mapping(source = "category.name", target = "category")
  ArticleDto articleToArticleDto(Article article);

  @Mapping(source = "guid", target = "id")
  @Mapping(source = "created", target = "created", qualifiedByName = {"convertEpochToZoneDateTime"})
  @Mapping(source = "updated", target = "updated", qualifiedByName = {"convertEpochToZoneDateTime"})
  @Mapping(source = "deleted", target = "deleted", qualifiedByName = {"convertEpochToZoneDateTime"})
  @Mapping(source = "attributes.author", target = "author")
  @Mapping(source = "attributes.sourceURL", target = "sourceUrl")
  @Mapping(source = "notebookGuid", target = "category.id")
  @Mapping(source = "tagGuids", target = "labelGuidList")
  Article noteToArticle(Note note);

  @AfterMapping
  default void setUpTagNameForArticle(List<String> tagNames, @MappingTarget Article article) {
    List<Label> labelGuidList = article.getLabelGuidList();
    for (int i = 0; i < tagNames.size(); i++) {
      labelGuidList.get(i).setName(tagNames.get(i));
    }
  }

  @Named("convertEpochToZoneDateTime")
  default ZonedDateTime convertEpochToZoneDateTime(long epoch) {
    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.of("Asia/Shanghai"));
  }

  @Named("convertZoneDateTimeToString")
  default String convertZoneDateTimeToString(ZonedDateTime dateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    return dateTime == null ? null : dateTime.format(formatter);
  }

  @StringToDateTime
  @Named("convertStringToLocalDate")
  default String convertStringToLocalDate(long epoch) {
    return new SimpleDateFormat("dd/MM/yyyy").format(new Date(epoch));
  }

  @Named("convertGuidToUrl")
  default String convertGuidToUrl(String guid) {
    return HTTPS_APP_YINXIANG_COM_FX + guid;
  }
}
