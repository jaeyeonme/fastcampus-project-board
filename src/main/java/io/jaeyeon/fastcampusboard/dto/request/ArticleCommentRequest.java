package io.jaeyeon.fastcampusboard.dto.request;

import io.jaeyeon.fastcampusboard.dto.ArticleCommentDto;
import io.jaeyeon.fastcampusboard.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCommentRequest {

    private Long articleId;
    private String content;

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }
}
