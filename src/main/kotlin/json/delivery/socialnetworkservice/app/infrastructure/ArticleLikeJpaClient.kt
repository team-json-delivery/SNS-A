package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.infrastructure.dto.ArticleLikeDto
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleLikeJpaClient : JpaRepository<ArticleLikeDto, Long> {
    fun findByArticleId(articleId: String): List<ArticleLikeDto>
    fun findByArticleIdIn(articleIds: List<String>): List<ArticleLikeDto>
}
