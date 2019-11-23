package top.ilovestudy.yinxiang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.ilovestudy.yinxiang.model.entites.Article;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

  @Query(value = "SELECT COUNT(*) AS count,CONCAT(EXTRACT(ISOYEAR FROM created), '-', EXTRACT(MONTH FROM created)) AS groupDate from ARTICLE where created > (NOW() - INTERVAL '1 YEAR') GROUP by groupDate", nativeQuery = true)
  List<Map> findALLArchiveGroupByMonthAndYearInLastYear();

  @Query(value = "SELECT COUNT(*) AS count,EXTRACT(YEAR FROM created) AS groupDate FROM ARTICLE WHERE created < (NOW() - INTERVAL '1 YEAR') GROUP BY groupDate", nativeQuery = true)
  List<Map> findALLArchiveGroupByYearInAYearAgo();
}
