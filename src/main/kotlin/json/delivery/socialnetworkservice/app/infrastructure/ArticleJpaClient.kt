package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.entity.ArticleDto
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleJpaClient : JpaRepository<ArticleDto, String>
