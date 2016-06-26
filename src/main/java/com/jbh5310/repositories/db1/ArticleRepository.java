package com.jbh5310.repositories.db1;

import com.jbh5310.domain.db1.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jbh5310 on 2016-06-26.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
