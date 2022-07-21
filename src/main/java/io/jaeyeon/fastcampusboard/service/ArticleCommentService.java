package io.jaeyeon.fastcampusboard.service;

import io.jaeyeon.fastcampusboard.domain.Article;
import io.jaeyeon.fastcampusboard.domain.ArticleComment;
import io.jaeyeon.fastcampusboard.domain.UserAccount;
import io.jaeyeon.fastcampusboard.dto.ArticleCommentDto;
import io.jaeyeon.fastcampusboard.repository.ArticleCommentRepository;
import io.jaeyeon.fastcampusboard.repository.ArticleRepository;
import io.jaeyeon.fastcampusboard.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId)
                .stream()
                .map(ArticleCommentDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveArticleComment(ArticleCommentDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.getArticleId());
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());
            articleCommentRepository.save(dto.toEntity(article, userAccount));
        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패, 댓글 작성에 필요한 정보를 찾을 수 없습니다. - {}", e.getLocalizedMessage());
        }
    }

    @Transactional
    public void updateArticleComment(ArticleCommentDto dto) {
        try {
            ArticleComment articleComment = articleCommentRepository.getReferenceById(dto.getArticleId());
            if (dto.getContent() != null) { articleComment.setContent(dto.getContent()); }
        } catch (EntityNotFoundException e) {
            log.warn("댓글 업데이트 실패, 댓글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    @Transactional
    public void deleteArticleComment(Long articleCommentId, String userId) {
        articleCommentRepository.deleteByIdAndUserAccount_UserId(articleCommentId, userId);
    }
}
