package top.ilovestudy.yinxiang.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import top.ilovestudy.yinxiang.IsolationTest;
import top.ilovestudy.yinxiang.model.entites.Group;
import top.ilovestudy.yinxiang.model.entites.Label;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Sql(scripts = "classpath:sql/init_multiple_labels_table.sql")
class LabelRepositoryTest extends IsolationTest {

  @Autowired
  LabelRepository labelRepository;

  @Test
  void shouldFindAllLabelsByArticleId() {
    List<Label> labels = labelRepository.findAllByArticleId("article_id_1");

    assertEquals(3, labels.size());
  }

  @Test
  void shouldReturnAllLabelsGroupByAndGroupByName() {
    List<Group> groups = labelRepository.queryAllGroupByName();
    assertEquals(2, groups.size());
    assertEquals(1, groups.stream().filter(obj -> obj.getName().equals("article1_tag_1")).findFirst().get().getCount());
    assertEquals(2, groups.stream().filter(obj -> obj.getName().equals("article1_tag_2")).findFirst().get().getCount());
  }
}