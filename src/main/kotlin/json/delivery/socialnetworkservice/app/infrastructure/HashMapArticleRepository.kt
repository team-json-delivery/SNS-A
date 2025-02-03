package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.exception.ArticleNotFoundException
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Repository
class HashMapArticleRepository : ArticleRepository {
    private val articleMap: ConcurrentMap<String, Article> = ConcurrentHashMap()
    override fun findById(id: String): Article {
        return articleMap[id] ?: throw ArticleNotFoundException("Article not found : $id")
    }

    override fun save(article: Article): Article {
        articleMap[article.id] = article
        return article
    }
}
