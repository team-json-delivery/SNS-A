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

        // TODO 중복 요청 검증 추가 필요. 서비스 레이어에서 할 것인지? following() 함수 내부에서 할 것인지?
        relation.following(followerId)

        relationRepository.save(relation)

        return relation
    }

    override fun unFollowUser(userId: UserId, unFollowerId: UserId): Relation {
        TODO("Not yet implemented")
    }

    override fun follower(userId: UserId): Relation {
        TODO("Not yet implemented")
    }
}
