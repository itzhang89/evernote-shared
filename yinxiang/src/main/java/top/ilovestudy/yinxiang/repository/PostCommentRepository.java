package top.ilovestudy.yinxiang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.ilovestudy.yinxiang.model.entites.PostComment;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

  @Query(value = "SELECT * FROM post_comment WHERE article_id = ?1", nativeQuery = true)
  List<PostComment> findAllByArticleId(String id);
}
