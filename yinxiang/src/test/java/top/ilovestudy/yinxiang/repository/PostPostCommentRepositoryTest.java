package top.ilovestudy.yinxiang.repository;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import top.ilovestudy.yinxiang.IsolationTest;
import top.ilovestudy.yinxiang.model.entites.PostComment;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Sql(scripts = "classpath:/sql/init_multiple_labels_table_with_comments.sql")
class PostPostCommentRepositoryTest extends IsolationTest {

  @Resource
  PostCommentRepository postCommentRepository;

  @Test
  void shouldGetAllPostComments() {
    List<PostComment> comments = postCommentRepository.findAll();

    assertEquals(8, comments.size());
  }
}