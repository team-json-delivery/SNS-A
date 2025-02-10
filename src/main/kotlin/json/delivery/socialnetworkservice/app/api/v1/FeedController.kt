package json.delivery.socialnetworkservice.app.api.v1

import jakarta.validation.Valid
import json.delivery.socialnetworkservice.app.application.FeedUseCase
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/feed")
class FeedController(private val feedUseCase: FeedUseCase) {

    @GetMapping("/{userId}")
    suspend fun feeds(@PathVariable("userId") userId: UserId): List<Article> {
        return feedUseCase.feeds(userId) ?: emptyList()
    }
}
