package top.ilovestudy.yinxiang.model.mapper;

import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteAttributes;
import org.junit.Test;
import top.ilovestudy.yinxiang.model.ArticleDto;
import top.ilovestudy.yinxiang.model.entites.Article;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static top.ilovestudy.yinxiang.model.mapper.ArticleMapper.HTTPS_APP_YINXIANG_COM_FX;
import static top.ilovestudy.yinxiang.model.mapper.ArticleMapper.INSTANCE;

public class ArticleMapperTest {

  @Test
  public void shouldConvertNoteToArticleDto() {
    // when
    Article article = new Article();
    article.setId("1234");
    article.setTitle("I am title");
    article.setCreated(ZonedDateTime.parse("2019-11-17T22:34:12.316+08:00[Asia/Shanghai]"));

    // given
    ArticleDto articleDto = INSTANCE.articleToArticleDto(article);

    // then
    assertEquals("11/17/2019", articleDto.getCreated());
    assertEquals(article.getTitle(), articleDto.getTitle());
    assertEquals(article.getId(), articleDto.getId());
    assertEquals(HTTPS_APP_YINXIANG_COM_FX + article.getId(), articleDto.getUrl());
  }

  @Test
  public void shouldConvertEpochToFormalDateTime() {
    String toLocalDate = INSTANCE.convertStringToLocalDate(1572071946000L);
    assertEquals("26/10/2019", toLocalDate);
  }

  @Test
  public void shouldConvertEpochToZoneTimeDate() {
    ZonedDateTime toLocalDate = INSTANCE.convertEpochToZoneDateTime(1572071946000L);
    assertEquals(ZonedDateTime.of(2019, 10, 26, 14, 39, 6, 0, ZoneId.of("Asia/Shanghai")), toLocalDate);
  }

  @Test
  public void shouldConvertNoteToArticle() {
    Note note = getTestNote();

    Article article = INSTANCE.noteToArticle(note);

    assertEquals(note.getGuid(), article.getId());
    assertEquals(note.getTitle(), article.getTitle());
    assertNotNull(article.getCreated());
    assertEquals(note.getUpdateSequenceNum(), article.getUpdateSequenceNum());
    assertEquals(note.getNotebookGuid(), article.getCategory().getId());
    assertEquals(note.getAttributes().getSourceURL(), article.getSourceUrl());
    assertEquals(note.getAttributes().getAuthor(), article.getAuthor());
    assertEquals(note.getTagGuids().get(0), article.getLabelGuidList().get(0).getTagGuid());
    assertEquals(note.getTagGuids().get(1), article.getLabelGuidList().get(1).getTagGuid());
  }

  private Note getTestNote() {
    Note note = new Note();
    note.setGuid("note_guid");
    note.setTitle("I am title");
    note.setCreated(1572071946000L);
    note.setUpdateSequenceNum(10);
    note.setContent("content");
    note.setNotebookGuid("notebook_guid");

    NoteAttributes attributes = new NoteAttributes();
    attributes.setSourceURL("wwww.ilovestudy.top");
    attributes.setAuthor("iLoveStudy");
    note.setAttributes(attributes);

    List<String> tagGuids = new ArrayList<>();
    tagGuids.add("tag_guid_1");
    tagGuids.add("tag_guid_2");
    note.setTagGuids(tagGuids);

    List<String> tagNames = new ArrayList<>();
    tagNames.add("tag_name_1");
    tagNames.add("tag_name_2");
    note.setTagNames(tagNames);

    return note;
  }
}