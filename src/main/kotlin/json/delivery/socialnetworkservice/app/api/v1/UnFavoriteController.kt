package json.delivery.socialnetworkservice.app.api.v1

import json.delivery.socialnetworkservice.app.api.v1.request.UnFavoriteRequest
import json.delivery.socialnetworkservice.app.api.v1.response.UnFavoriteResponse
import json.delivery.socialnetworkservice.app.application.UnFavoriteUsecase
import json.delivery.socialnetworkservice.app.domain.UserId
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/article")
class UnFavoriteController(
    private val unFavoriteUsecase: UnFavoriteUsecase,
) {
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{articleId}/favorite")
    fun unFavoriteArticle(
        @PathVariable articleId: String,
        @Validated @RequestBody request: UnFavoriteRequest,
    ): UnFavoriteResponse {
        return UnFavoriteResponse(unFavoriteUsecase.execute(UserId(request.userId), articleId))
    }
}
