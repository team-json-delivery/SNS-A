package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.infrastructure.ArticleRepository
import json.delivery.socialnetworkservice.app.infrastructure.RelationRepository
import org.springframework.stereotype.Service

@Service
class FeedService(
    private val articleRepository: ArticleRepository,
    private val relationRepository: RelationRepository,
) : FeedUseCase {

    override suspend fun feeds(userId: UserId): List<Article> {
        val relation: Relation = relationRepository.findByUserId(userId) ?: return emptyList()
        val followingIds: List<UserId> = relation.followings.map { it.userId }
        val articles: List<Article> = articleRepository.findAllByAuthorIds(followingIds)
        return articles.filter { article -> followingIds.contains(article.authorId) }
    }
}
