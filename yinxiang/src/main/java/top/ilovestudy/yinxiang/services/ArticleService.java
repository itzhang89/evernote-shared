package top.ilovestudy.yinxiang.services;

import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.thrift.TException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import top.ilovestudy.yinxiang.model.ArchiveDto;
import top.ilovestudy.yinxiang.model.ArticleDto;
import top.ilovestudy.yinxiang.model.CategoryDto;
import top.ilovestudy.yinxiang.model.LabelDto;
import top.ilovestudy.yinxiang.model.entites.Article;
import top.ilovestudy.yinxiang.model.entites.Label;
import top.ilovestudy.yinxiang.model.mapper.ArticleMapper;
import top.ilovestudy.yinxiang.model.mapper.CategoryMapper;
import top.ilovestudy.yinxiang.model.mapper.LabelMapper;
import top.ilovestudy.yinxiang.repository.ArticleRepository;
import top.ilovestudy.yinxiang.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class ArticleService {

  private final EverNoteWebService everNoteWebService;

  private final ArticleRepository articleRepository;


  public ArticleService(EverNoteWebService everNoteWebService, ArticleRepository articleRepository, LabelRepository LabelRepository) {
    this.everNoteWebService = everNoteWebService;
    this.articleRepository = articleRepository;
  }

  public List<ArchiveDto> getArchives() {
    List<ArchiveDto> lists = new ArrayList<>();
    lists.add(new ArchiveDto("Decob14 2018", 20, "#"));
    lists.add(new ArchiveDto("September 2018", 12, "#"));
    lists.add(new ArchiveDto("July 2018", 14, "#"));
    lists.add(new ArchiveDto("June 2018", 16, "#"));
    lists.add(new ArchiveDto("May 2018 ", 80, "#"));

    return lists;
  }

  public List<LabelDto> getCloudTags() throws EDAMUserException, EDAMSystemException, TException {
    List<Label> labels = everNoteWebService.getTags().stream().map(LabelMapper.INSTANCE::tagToLabel).collect(Collectors.toList());
    return labels.stream().map(LabelMapper.INSTANCE::labelToLabelDto).collect(Collectors.toList());
  }

  public List<ArticleDto> getPopularArticles() {
    List<ArticleDto> lists = new ArrayList<>();
    ArticleDto articleDto1 = getDefaultArticle("A Loving Heart is the Truest Wisdom");
    lists.add(articleDto1);

    ArticleDto articleDto2 = getDefaultArticle("Great Things Never Came from Comfort Zone");
    lists.add(articleDto2);

    ArticleDto articleDto3 = getDefaultArticle("The Popular Articles popular paper for Zone");
    lists.add(articleDto3);

    return lists;
  }


  public List<CategoryDto> getCategoriesFromNotebooks() throws TException, EDAMUserException, EDAMSystemException {
    return everNoteWebService.getNoteBooks().stream().map(CategoryMapper.INSTANCE::noteBookToCategoryDto).collect(Collectors.toList());
  }

  public ArticleDto findArticleDtoById(String id) {
    Article article = articleRepository.findById(id).orElse(null);
    return ArticleMapper.INSTANCE.articleToArticleDto(article);
  }

  public List<ArticleDto> findSharedArticleDtoList() {
    List<Article> articles = findSharedArticles();
    return articles.stream().map(ArticleMapper.INSTANCE::articleToArticleDto).collect(Collectors.toList());
  }

  private List<Article> findSharedArticles() {
    return articleRepository.findAll();
  }

  // FIXME: 2019-11-18 template for the to sync evernote service to update Note Detail
  private boolean syncCondition(List<Article> articlesInDatabase) {
    return articlesInDatabase.size() == 0;
  }

  private ArticleDto getDefaultArticle(String s) {
    return ArticleDto.builder()
        .title(s)
        .briefIntro("A small river named Duden flows by their place and supplies it with the necessary regelialia.")
        .category("my category")
        .created("June 28, 2019")
        .author("iLoveStudy")
        .url("single")
        .commentNum(5)
        .avatar("/images/image_1.jpg")
        .build();
  }
}
