package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.application.dto.UnFavoriteUsecaseOutputDto
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.infrastructure.ArticleRepository
import org.springframework.stereotype.Service

@Service
class UnFavoriteService(
    private val articleRepository : ArticleRepository
) : UnFavoriteUsecase {
    override fun execute(userId: UserId, articleId: String): UnFavoriteUsecaseOutputDto {
        val article = articleRepository.findById(articleId)

        article.disLike(userId)

        articleRepository.save(article)

        return UnFavoriteUsecaseOutputDto(userId, articleId)
    }
}
