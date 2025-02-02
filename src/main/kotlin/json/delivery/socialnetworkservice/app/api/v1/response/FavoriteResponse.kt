package json.delivery.socialnetworkservice.app.api.v1.response

import json.delivery.socialnetworkservice.app.application.dto.FavoriteUsecaseOutputDto

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
