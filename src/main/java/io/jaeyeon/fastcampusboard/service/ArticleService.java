package io.jaeyeon.fastcampusboard.service;

import io.jaeyeon.fastcampusboard.domain.Article;
import io.jaeyeon.fastcampusboard.domain.type.SearchType;
import io.jaeyeon.fastcampusboard.dto.ArticleDto;
import io.jaeyeon.fastcampusboard.dto.ArticleWithCommentsDto;
import io.jaeyeon.fastcampusboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;


    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        switch (searchType) {
            case TITLE: articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT: articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID: articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME: articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG: articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };
        return null;
    }

    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    @Transactional
    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    @Transactional
    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.getId());
            if (dto.getTitle() != null) { article.setTitle(dto.getTitle()); }
            if (dto.getContent() != null) { article.setContent(dto.getContent()); }
            article.setHashtag(dto.getHashtag());
        } catch (EntityNotFoundException e) {
            // {}: String interpolation
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    @Transactional
    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }
}
