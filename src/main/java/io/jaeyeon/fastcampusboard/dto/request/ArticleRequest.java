package io.jaeyeon.fastcampusboard.dto.request;

import io.jaeyeon.fastcampusboard.dto.ArticleDto;
import io.jaeyeon.fastcampusboard.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {

    private String title;
    private String content;
    private String hashtag;

    public static ArticleRequest of(String title, String content, String hashtag) {
        return new ArticleRequest(title, content, hashtag);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtag
        );
    }
}
