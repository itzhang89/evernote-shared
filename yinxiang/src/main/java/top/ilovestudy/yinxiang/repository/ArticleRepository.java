package top.ilovestudy.yinxiang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.ilovestudy.yinxiang.model.entites.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
}
