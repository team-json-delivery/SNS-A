package json.delivery.socialnetworkservice.app.api.v1

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import json.delivery.socialnetworkservice.app.application.ArticleService
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/article")
class ArticleController(private val articleService: ArticleService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{articleId}")
    suspend fun getArticle(@PathVariable("articleId") articleId: String) =
        articleService.get(articleId)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    suspend fun registerArticle(@Validated @RequestBody request: ArticleRequest) = articleService.register(
        with(request) { Article.create(UserId(authorId), content) },
    )

    data class ArticleRequest(
        @field:Positive
        val authorId: Long,
        @field:NotEmpty
        val content: String,
    )
}
