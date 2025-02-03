package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.infrastructure.ArticleRepository
import org.springframework.stereotype.Service

@Service
class FavoriteService(
    private val articleRepository : ArticleRepository
) : FavoriteUsecase {
    override fun execute(userId: UserId, articleId: String): FavoriteUsecaseOutputDto {
        val article = articleRepository.findById(articleId)

        article.addLike(userId)

        articleRepository.save(article)

        return FavoriteUsecaseOutputDto(userId, articleId)
    }
}
