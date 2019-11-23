package top.ilovestudy.yinxiang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.ilovestudy.yinxiang.model.entites.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {
}
