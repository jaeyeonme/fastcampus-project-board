package io.jaeyeon.fastcampusboard.service;

import io.jaeyeon.fastcampusboard.dto.ArticleCommentDto;
import io.jaeyeon.fastcampusboard.repository.ArticleCommentRepository;
import io.jaeyeon.fastcampusboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public List<ArticleCommentDto> searchArticleComment(long articleId) {
        return List.of();
    }
}
