package top.ilovestudy.yinxiang.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.ilovestudy.yinxiang.model.entites.Article;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static top.ilovestudy.yinxiang.utils.JsonUtils.readJsonFileToObject;
import static top.ilovestudy.yinxiang.utils.JsonUtils.readObjectListFromJsonFile;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticleRepositoryTest {

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
    articleRepository.deleteAll();
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
    articleRepository.deleteAll();
    List<Article> articles = readObjectListFromJsonFile("base_shared_articles.json");

    articleRepository.saveAll(articles);

    assertNotNull(articles);
  }


}