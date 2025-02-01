package json.delivery.socialnetworkservice.app.api.v1

import json.delivery.socialnetworkservice.app.api.v1.request.FavoriteRequest
import json.delivery.socialnetworkservice.app.api.v1.response.FavoriteResponse
import json.delivery.socialnetworkservice.app.application.FavoriteUsecase
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
class FavoriteController(
    private val favoriteUsecase: FavoriteUsecase,
) {
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{articleId}/favorite")
    fun favoriteArticle(
        @PathVariable articleId: String,
        @Validated @RequestBody request: FavoriteRequest,
    ): FavoriteResponse {
        return favoriteUsecase.execute(UserId(request.userId), articleId).let { FavoriteResponse(it) };
    }
}
