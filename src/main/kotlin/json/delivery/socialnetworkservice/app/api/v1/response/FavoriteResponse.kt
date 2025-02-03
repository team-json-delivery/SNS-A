package json.delivery.socialnetworkservice.app.api.v1.response

import json.delivery.socialnetworkservice.app.application.FavoriteUsecaseOutputDto
import json.delivery.socialnetworkservice.app.domain.Following
import json.delivery.socialnetworkservice.app.domain.Relation
import java.time.ZonedDateTime

data class FavoriteResponse(
    val userId: Long,
    val articleId: String,
) {
    companion object {
        operator fun invoke(favoriteUsecaseOutputDto: FavoriteUsecaseOutputDto): FavoriteResponse = FavoriteResponse(
            userId = favoriteUsecaseOutputDto.userId.id,
            articleId = favoriteUsecaseOutputDto.articleId
        )
    }
}
