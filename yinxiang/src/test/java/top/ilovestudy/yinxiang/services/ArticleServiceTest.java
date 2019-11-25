package top.ilovestudy.yinxiang.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import top.ilovestudy.yinxiang.BaseSpringTest;
import top.ilovestudy.yinxiang.model.LabelDto;
import top.ilovestudy.yinxiang.model.entites.Label;
import top.ilovestudy.yinxiang.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class ArticleServiceTest extends BaseSpringTest {

  @MockBean
  LabelRepository labelRepository;

  @SpyBean
  ArticleService articleService;

  @Test
  void findCloudLabelById() {
  }

  @Test
  void findCloudLabelList() {
    Label label1 = new Label(1L, "tag_guid_1", "tag_name_1");
    Label label2 = new Label(2L, "tag_guid_1", "tag_name_1");
    Label label3 = new Label(3L, "tag_guid_2", "tag_name_2");
    List labels = new ArrayList();
    labels.add(label1);
    labels.add(label2);
    labels.add(label3);
    Mockito.when(labelRepository.findAll()).thenReturn(labels);

    List<LabelDto> cloudLabelList = articleService.findCloudLabelList();

    assertEquals(2, cloudLabelList.size());
  }

  @Test
  void findCloudLabelListByArticleId() {
  }
}