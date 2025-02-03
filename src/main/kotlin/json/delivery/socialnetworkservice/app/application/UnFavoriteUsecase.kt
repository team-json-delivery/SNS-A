package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.application.dto.UnFavoriteUsecaseOutputDto
import json.delivery.socialnetworkservice.app.domain.UserId

interface UnFavoriteUsecase {
    fun execute(userId: UserId, articleId: String): UnFavoriteUsecaseOutputDto
}
