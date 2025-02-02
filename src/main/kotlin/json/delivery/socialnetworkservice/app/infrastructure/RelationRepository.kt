package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId

interface RelationRepository {
    fun save(relation: Relation)

    fun findAll(): List<Relation>

    fun findByUserId(userId: UserId): Relation
}
