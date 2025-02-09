package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId

interface FeedUseCase {
    suspend fun feeds(userId: UserId): List<Article>?
}
