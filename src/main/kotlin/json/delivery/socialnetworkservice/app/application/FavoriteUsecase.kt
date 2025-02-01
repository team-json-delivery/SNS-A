package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.UserId

interface FavoriteUsecase {
    fun execute(userId: UserId, articleId: String): FavoriteUsecaseOutputDto
}
