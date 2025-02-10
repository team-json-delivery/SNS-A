package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.infrastructure.dto.ArticleDto
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleJpaClient : JpaRepository<ArticleDto, String> {
    fun findAllByAuthorIdIn(authorIds: List<Long>): List<ArticleDto>
}
