package top.ilovestudy.yinxiang.model.mapper;

import com.evernote.edam.type.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import top.ilovestudy.yinxiang.model.ArticleDto;
import top.ilovestudy.yinxiang.model.entites.Article;
import top.ilovestudy.yinxiang.model.mapper.annotion.StringToDateTime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

  @Mapping(target = "briefIntro", ignore = true)
  @Mapping(target = "original", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(target = "postComments", ignore = true)
  @Mapping(target = "shared", ignore = true)
  @Mapping(target = "id", source = "guid")
  @Mapping(target = "created", source = "created", qualifiedByName = {"convertEpochToZoneDateTime"})
  @Mapping(target = "updated", source = "updated", qualifiedByName = {"convertEpochToZoneDateTime"})
  @Mapping(target = "deleted", source = "deleted", qualifiedByName = {"convertEpochToZoneDateTime"})
  @Mapping(target = "author", source = "attributes.author")
  @Mapping(target = "sourceUrl", source = "attributes.sourceURL")
  @Mapping(target = "category.id", source = "notebookGuid")
  @Mapping(target = "labelGuidList", source = "tagGuids")
  Article noteToArticle(Note note);

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
