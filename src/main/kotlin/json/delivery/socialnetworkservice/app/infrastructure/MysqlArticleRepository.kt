package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.exception.ArticleNotFoundException
import json.delivery.socialnetworkservice.app.infrastructure.dto.ArticleDtoFactory
import json.delivery.socialnetworkservice.app.infrastructure.dto.ArticleLikeDto
import org.springframework.stereotype.Repository

@Repository
class MysqlArticleRepository(
    private val articleJpaClient: ArticleJpaClient,
    private val articleLikeJpaClient: ArticleLikeJpaClient
) : ArticleRepository {
    override fun findById(id: String): Article {
        val articleEntity =
            articleJpaClient.findById(id).orElseThrow { ArticleNotFoundException("Article not found : $id") }
        val likes = articleLikeJpaClient.findByArticleId(id).map { it.userId }
        return Article.of(
            id = articleEntity.id,
            authorId = articleEntity.authorId,
            content = articleEntity.content,
            createdAt = articleEntity.createdAt,
            updatedAt = articleEntity.updatedAt,
            likes = likes
        )
    }

    override fun save(article: Article): Article {
        val articleEntity = ArticleDtoFactory.toDto(article)
        articleJpaClient.save(articleEntity)

        article.likes.forEach { userId ->
            val likeEntity = ArticleLikeDto(articleId = article.id, userId = userId.id)
            articleLikeJpaClient.save(likeEntity)
        }

        return article
    }

    override suspend fun findAllByAuthorIds(userIds: List<UserId>): List<Article> {
        val articles = articleJpaClient.findAllByAuthorIdIn(userIds.map { it.id })
        val likes = articleLikeJpaClient.findByArticleIdIn(articles.map { it.id })
            .groupBy { it.articleId }

        return articles.map { article ->
            Article.of(
                id = article.id,
                authorId = article.authorId,
                createdAt = article.createdAt,
                likes = likes[article.id]?.map { it.id!! } ?: listOf(),
                content = article.content,
                updatedAt = article.updatedAt,
            )
        }
    }
}
