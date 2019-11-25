package top.ilovestudy.yinxiang.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.ilovestudy.yinxiang.IsolationTest;
import top.ilovestudy.yinxiang.model.entites.Article;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static top.ilovestudy.yinxiang.utils.JsonUtils.readJsonFileToObject;
import static top.ilovestudy.yinxiang.utils.JsonUtils.readObjectListFromJsonFile;

class ArticleRepositoryTest extends IsolationTest {

  @Autowired
  ArticleRepository articleRepository;

  @Test
  void shouldDeserializeArticleObjectFromString() {
    Article article = readJsonFileToObject("article.json", Article.class);
    assertEquals("840b5190-7493-7493-7493-9ce6507e4773", article.getId());
  }

  @Test
  void shouldDontGenerateTheUUIDWhenSaveArticleEntityGivenExistedUUIDString() {
    Article article = readObjectListFromJsonFile("base_shared_articles.json").get(0);
    if (articleRepository.findById(article.getId()).isPresent()) {
      articleRepository.deleteById(article.getId());
    }

    articleRepository.save(article);

    Article articleInDatabase = articleRepository.findById(article.getId()).orElse(null);
    assertEquals(article.getId(), articleInDatabase.getId());
  }

  @Test
  void shouldSaveOneArticleInDatabase() {
    Article article = readObjectListFromJsonFile("base_shared_articles.json").get(0);
    if (articleRepository.findById(article.getId()).isPresent()) {
      articleRepository.deleteById(article.getId());
    }

    articleRepository.save(article);

    Article articleInDatabase = articleRepository.findById(article.getId()).orElse(null);

    assertEquals(article.getTitle(), articleInDatabase.getTitle());
    assertEquals(article.getCreated(), articleInDatabase.getCreated());
  }

  @Test
  void shouldSaveAllArticles() {
    List<Article> articles = readObjectListFromJsonFile("base_shared_articles.json");

    articleRepository.saveAll(articles);

    assertNotNull(articles);
  }

}