package top.ilovestudy.yinxiang.services;

import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Note;
import com.evernote.thrift.TException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;
import top.ilovestudy.yinxiang.IsolationTest;
import top.ilovestudy.yinxiang.model.entites.Article;
import top.ilovestudy.yinxiang.model.entites.Category;
import top.ilovestudy.yinxiang.model.entites.Label;
import top.ilovestudy.yinxiang.repository.ArticleRepository;
import top.ilovestudy.yinxiang.repository.CategoryRepository;
import top.ilovestudy.yinxiang.repository.LabelRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static top.ilovestudy.yinxiang.utils.JsonUtils.readNoteListFromJsonFile;


class SyncEverNoteServiceTest extends IsolationTest {

  @Autowired
  LabelRepository labelRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  SyncEverNoteService syncEverNoteService;

  @SpyBean
  EverNoteWebService everNoteWebService;

  @Test
  void shouldSaveAllArticlesAndDetailsInDatabaseWhenSyncNoteFromEverNote() throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    List<Note> notes = readNoteListFromJsonFile("base_notes.json");
    Mockito.when(everNoteWebService.getAllNotes()).thenReturn(notes);

    syncEverNoteService.syncAllArticlesAndDetailsAndSaveInDatabase();

    List<Article> articles = articleRepository.findAll();
    assertEquals(1, articles.size());
    Article article = articles.get(0);
    assertEquals(true, article.isShared());
    assertNotNull(article.getContent());
    assertNotNull(articleRepository.findById(article.getCategory().getId()));
    List<Label> labels = labelRepository.findAll();
    assertEquals(1, labels.size());
  }

  @Test
  @Sql(scripts = "classpath:sql/init_label_table.sql")
  void shouldUpdateLabelsNameInDatabaseWhenSyncTagGivenGuidList() throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    List<Label> labelsBeforeSync = labelRepository.findAll();
    assertEquals(1, labelsBeforeSync.size());
    assertNull(labelsBeforeSync.get(0).getName());

    syncEverNoteService.syncAllLabelsFromEverNoteAndSaveInDatabase();

    List<Label> labelsAfterSync = labelRepository.findAll();
    assertEquals(1, labelsBeforeSync.size());
    assertNotNull(labelsAfterSync.get(0).getName());
  }

  @Test
  @Sql(scripts = "classpath:sql/init_label_table.sql")
  void shouldUpdateCategoriesNameInDatabaseWhenSyncCategories() throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    List<Category> categoriesBeforeSync = categoryRepository.findAll();
    assertEquals(1, categoriesBeforeSync.size());
    assertNull(categoriesBeforeSync.get(0).getName());

    syncEverNoteService.syncAllCategoriesFromEverNoteAndSaveInDatabase();

    List<Category> categoriesAfterSync = categoryRepository.findAll();
    assertEquals(1, categoriesBeforeSync.size());
    assertNotNull(categoriesAfterSync.get(0).getName());
  }
}