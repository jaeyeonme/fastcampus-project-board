package io.jaeyeon.fastcampusboard.controller;

import io.jaeyeon.fastcampusboard.dto.UserAccountDto;
import io.jaeyeon.fastcampusboard.dto.request.ArticleCommentRequest;
import io.jaeyeon.fastcampusboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {

        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "jaeyeon", "pw", "cjyeon1022@gmail.com", null, null
        )));

        return "redirect:/articles/" + articleCommentRequest.getArticleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }
}
