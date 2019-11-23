package top.ilovestudy.yinxiang.services;

import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.Tag;
import com.evernote.thrift.TException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.ilovestudy.yinxiang.model.entites.Article;
import top.ilovestudy.yinxiang.model.entites.Category;
import top.ilovestudy.yinxiang.model.entites.Label;
import top.ilovestudy.yinxiang.model.mapper.ArticleMapper;
import top.ilovestudy.yinxiang.model.response.DetailForPublic;
import top.ilovestudy.yinxiang.repository.ArticleRepository;
import top.ilovestudy.yinxiang.repository.CategoryRepository;
import top.ilovestudy.yinxiang.repository.LabelRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyncEverNoteService {
  private static final String SHARE_NOTE_DETAIL_FOR_PUBLIC = "https://app.yinxiang.com/third/share/note/detailForPublic?guid=";
  private final EverNoteWebService everNoteWebService;
  private final ArticleRepository articleRepository;
  private final LabelRepository labelRepository;
  private final CategoryRepository categoryRepository;
  @Resource
  private RestTemplate restTemplate;

  public SyncEverNoteService(EverNoteWebService everNoteWebService, ArticleRepository articleRepository, LabelRepository labelRepository, CategoryRepository categoryRepository) {
    this.everNoteWebService = everNoteWebService;
    this.articleRepository = articleRepository;
    this.labelRepository = labelRepository;
    this.categoryRepository = categoryRepository;
  }

  @Scheduled(cron = "${yinxiang.sync.schedule}")
  public void syncAllTasks() throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    syncAllArticlesAndDetailsAndSaveInDatabase();
    syncAllLabelsFromEverNoteAndSaveInDatabase();
    syncAllCategoriesFromEverNoteAndSaveInDatabase();
  }

  void syncAllArticlesAndDetailsAndSaveInDatabase() throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    List<Article> articles = everNoteWebService.getAllNotes().stream().map(ArticleMapper.INSTANCE::noteToArticle).collect(Collectors.toList());
    List<Article> articlesDetails = articles.stream().filter(article -> findPublishNoteAndSetContentAndBriefIntro(article)).collect(Collectors.toList());
    articleRepository.saveAll(articlesDetails);
  }

  void syncAllLabelsFromEverNoteAndSaveInDatabase() throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    List<Label> labels = labelRepository.findAll();
    for (Label label : labels) {
      Tag tag = everNoteWebService.getTagById(label.getId());
      label.setName(tag.getName());
    }
    labelRepository.saveAll(labels);
  }

  void syncAllCategoriesFromEverNoteAndSaveInDatabase() throws EDAMUserException, TException, EDAMSystemException, EDAMNotFoundException {
    List<Category> categories = categoryRepository.findAll();
    for (Category category : categories) {
      Notebook notebook = everNoteWebService.getNoteBookById(category.getId());
      category.setName(notebook.getName());
    }
    categoryRepository.saveAll(categories);
  }

  private DetailForPublic getNoteDetailForPublic(final String guid) {
    String url = SHARE_NOTE_DETAIL_FOR_PUBLIC + guid;
    return restTemplate.getForObject(url, DetailForPublic.class);
  }

  private boolean findPublishNoteAndSetContentAndBriefIntro(Article article) {
    DetailForPublic noteDetailForPublic = getNoteDetailForPublic(article.getId());
    if (noteDetailForPublic.getCode() == HttpStatus.OK.value()) {
      article.setContent(noteDetailForPublic.getContent().getHtml());
      article.setBriefIntro(noteDetailForPublic.getContent().getSummary());
      article.setShared(true);
      return true;
    }
    return false;
  }

}
