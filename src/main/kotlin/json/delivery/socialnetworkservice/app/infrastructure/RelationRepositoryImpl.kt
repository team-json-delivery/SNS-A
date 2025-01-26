package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import org.springframework.stereotype.Repository

@Repository
class RelationRepositoryImpl : RelationRepository {

    override fun findByUserId(userId: UserId): Relation {
        // TODO 구현 필요
        return Relation(userId)
    }
}
