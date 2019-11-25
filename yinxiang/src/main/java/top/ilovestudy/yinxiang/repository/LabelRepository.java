package top.ilovestudy.yinxiang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.ilovestudy.yinxiang.model.entites.Label;

import java.security.acl.Group;
import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {

  @Query(value = "SELECT * FROM label WHERE article_id = ?1", nativeQuery = true)
  List<Label> findAllByArticleId(String articleId);

  @Query(value = "SELECT name,COUNT(*) AS count FROM label GROUP BY name", nativeQuery = true)
  List<Group> queryAllGroupByName();
}
