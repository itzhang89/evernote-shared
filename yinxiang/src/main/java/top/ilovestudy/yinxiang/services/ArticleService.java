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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  public List<ArchiveDto> findArchives() {
    List<Map> allArchiveGroupByMonthAndYearInLastYear = articleRepository.findAllArchiveGroupByMonthAndYearInLastYear();
    List<Map> allArchiveGroupByYearInAYearAgo = articleRepository.findAllArchiveGroupByYearInAYearAgo();
    return Stream.concat(allArchiveGroupByMonthAndYearInLastYear.stream(), allArchiveGroupByYearInAYearAgo.stream())
        .map(obj -> ArchiveDto.of(Integer.valueOf(obj.get("count").toString()), obj.get("groupDate").toString()))
        .sorted().collect(Collectors.toList());
  }

  public LabelDto findCloudLabelById(String id) {
    Label label = labelRepository.findById(id).orElse(null);
    return LabelMapper.INSTANCE.labelToLabelDto(label);
  }

  public List<LabelDto> findCloudLabelList() {
    List<Label> labels = labelRepository.findAll();
    List<Label> distinctLabels = labels.stream().distinct().collect(Collectors.toList());
    return distinctLabels.stream().map(LabelMapper.INSTANCE::labelToLabelDto).collect(Collectors.toList());
  }

  public List<LabelDto> findCloudLabelListByArticleId(String articleId) {
    List<Label> labels = labelRepository.findAllByArticleId(articleId);
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
    List<Map> allCategoriesGroup = articleRepository.findAllCategoriesGroup();
    return allCategoriesGroup.stream().map(obj ->
        new CategoryDto(obj.get("id").toString(), obj.get("name").toString(), Integer.valueOf(obj.get("count").toString()))
    ).sorted().collect(Collectors.toList());
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
