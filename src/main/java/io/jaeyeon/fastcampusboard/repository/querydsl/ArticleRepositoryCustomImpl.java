package io.jaeyeon.fastcampusboard.repository.querydsl;

import io.jaeyeon.fastcampusboard.domain.Article;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static io.jaeyeon.fastcampusboard.domain.QArticle.article;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {

        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}
