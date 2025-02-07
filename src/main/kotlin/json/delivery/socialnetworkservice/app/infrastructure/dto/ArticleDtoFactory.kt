package json.delivery.socialnetworkservice.app.mapper

import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.entity.ArticleDto

object ArticleDtoFactory {

    fun toDto(article: Article): ArticleDto {
        return ArticleDto(
            id = article.id,
            authorId = article.authorId.id,
            content = article.getContent(),
            createdAt = article.createdAt,
            updatedAt = article.getUpdatedAt()
        )
    }
}
