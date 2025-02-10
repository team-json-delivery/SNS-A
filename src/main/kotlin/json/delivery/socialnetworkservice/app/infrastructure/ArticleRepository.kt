package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId

interface ArticleRepository {
    fun findById(id: String): Article
    fun save(article: Article): Article
    suspend fun findAllByAuthorIds(userIds: List<UserId>): List<Article>
}
