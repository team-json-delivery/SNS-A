package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Article

interface ArticleUseCase {
    suspend fun get(articleId: String): Article
    suspend fun register(article: Article): Article
}
