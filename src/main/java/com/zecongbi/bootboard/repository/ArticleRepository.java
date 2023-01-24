package com.zecongbi.bootboard.repository;

import com.zecongbi.bootboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
