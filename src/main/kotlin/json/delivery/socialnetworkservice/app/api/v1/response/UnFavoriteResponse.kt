package json.delivery.socialnetworkservice.app.api.v1.response

import json.delivery.socialnetworkservice.app.application.dto.UnFavoriteUsecaseOutputDto

class UnFavoriteResponse (
    val userId: Long,
    val articleId: String,
) {
    companion object {
        operator fun invoke(favoriteUsecaseOutputDto: UnFavoriteUsecaseOutputDto): UnFavoriteResponse = UnFavoriteResponse(
            userId = favoriteUsecaseOutputDto.userId.id,
            articleId = favoriteUsecaseOutputDto.articleId
        )
    }
}
