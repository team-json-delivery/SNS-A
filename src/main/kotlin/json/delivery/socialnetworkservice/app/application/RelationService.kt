package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.infrastructure.RelationRepository
import org.springframework.stereotype.Service

@Service
class RelationService(
    private val relationRepository: RelationRepository,
) : RelationUseCase {
    override fun followUser(userId: UserId, followerId: UserId): Relation {
        // Relation 조회
        val relation = relationRepository.findByUserId(userId)

        relation.following(followerId)

        relationRepository.save(relation)

        return relation
    }

    override fun unFollowUser(userId: UserId, followerId: UserId): Relation {
        TODO("Not yet implemented")
    }

    override fun follower(userId: UserId): Relation {
        TODO("Not yet implemented")
    }
}
