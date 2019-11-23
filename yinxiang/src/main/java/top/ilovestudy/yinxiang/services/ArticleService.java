package top.ilovestudy.yinxiang.services;

import org.springframework.stereotype.Service;
import top.ilovestudy.yinxiang.model.ArchiveDto;
import top.ilovestudy.yinxiang.model.ArticleDto;
import top.ilovestudy.yinxiang.model.CategoryDto;
import top.ilovestudy.yinxiang.model.LabelDto;
import top.ilovestudy.yinxiang.model.entites.Article;
import top.ilovestudy.yinxiang.model.entites.Category;
import top.ilovestudy.yinxiang.model.entites.Label;
import top.ilovestudy.yinxiang.model.mapper.ArticleMapper;
import top.ilovestudy.yinxiang.model.mapper.CategoryMapper;
import top.ilovestudy.yinxiang.model.mapper.LabelMapper;
import top.ilovestudy.yinxiang.repository.ArticleRepository;
import top.ilovestudy.yinxiang.repository.CategoryRepository;
import top.ilovestudy.yinxiang.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final CategoryRepository categoryRepository;
  private final LabelRepository labelRepository;


  public ArticleService(ArticleRepository articleRepository, LabelRepository labelRepository, CategoryRepository categoryRepository) {
    this.articleRepository = articleRepository;
    this.categoryRepository = categoryRepository;
    this.labelRepository = labelRepository;
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

  public LabelDto findCloudLabelById(String id) {
    Label label = labelRepository.findById(id).orElse(null);
    return LabelMapper.INSTANCE.labelToLabelDto(label);
  }

  public List<LabelDto> findCloudLabelList() {
    List<Label> labels = labelRepository.findAll();
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

  public CategoryDto findCategoryDtoById(String id) {
    Category category = categoryRepository.findById(id).orElse(null);
    return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
  }

  public List<CategoryDto> findCategoryDtoList() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream().map(CategoryMapper.INSTANCE::categoryToCategoryDto).collect(Collectors.toList());
  }

  public ArticleDto findArticleDtoById(String id) {
    Article article = articleRepository.findById(id).orElse(null);
    return ArticleMapper.INSTANCE.articleToArticleDto(article);
  }

  public List<ArticleDto> findSharedArticleDtoList() {
    List<Article> articles = articleRepository.findAll();
    return articles.stream().map(ArticleMapper.INSTANCE::articleToArticleDto).collect(Collectors.toList());
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
