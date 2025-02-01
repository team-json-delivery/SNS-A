package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.domain.Article

interface ArticleRepository {
    fun findById(id: String): Article
    fun save(article: Article): Article
}
