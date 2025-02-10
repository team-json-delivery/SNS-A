package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.infrastructure.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) : ArticleUseCase {

    override suspend fun get(articleId: String): Article =
        articleRepository.findById(articleId)

    override suspend fun register(article: Article): Article =
        runCatching {
            articleRepository.save(article)
        }.getOrElse { throw Exception("article save error: ${it.message}") }
}
