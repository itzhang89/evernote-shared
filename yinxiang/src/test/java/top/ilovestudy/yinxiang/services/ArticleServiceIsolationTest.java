package top.ilovestudy.yinxiang.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import top.ilovestudy.yinxiang.IsolationTest;
import top.ilovestudy.yinxiang.model.ArchiveDto;

import java.util.List;

import static org.junit.Assert.assertEquals;

class ArticleServiceIsolationTest extends IsolationTest {

  @Autowired
  ArticleService articleService;

  @Test
  @Sql(scripts = "classpath:/sql/init_label_table.sql")
  void shouldSortedArchivesTitleFromDatabase() {
    List<ArchiveDto> archives = articleService.findArchives();

    assertEquals(1, archives.size());
    assertEquals(1, archives.get(0).getCount());
    assertEquals("2019-7", archives.get(0).getDate());
  }
}