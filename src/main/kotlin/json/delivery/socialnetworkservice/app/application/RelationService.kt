package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import org.springframework.stereotype.Service

@Service
class RelationService : RelationUseCase {
    override fun followUser(userId: UserId, followerId: UserId): Relation {
        // Relation 조회

        // follow 추가

        // Relation 영속화
        TODO("Not yet implemented")
    }

    override fun unFollowUser(userId: UserId, followerId: UserId): Relation {
        TODO("Not yet implemented")
    }

    override fun follower(userId: UserId): Relation {
        TODO("Not yet implemented")
    }
}
