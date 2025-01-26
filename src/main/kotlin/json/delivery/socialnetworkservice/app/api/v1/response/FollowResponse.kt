package json.delivery.socialnetworkservice.app.api.v1.response

import json.delivery.socialnetworkservice.app.domain.Following
import json.delivery.socialnetworkservice.app.domain.Relation
import java.time.ZonedDateTime

data class FollowResponse(
    val userId: Long,
    val followings: List<FollowingResponse>,
) {
    data class FollowingResponse(
        val userId: Long,
        val createdAt: ZonedDateTime,
    ) {
        companion object {
            operator fun invoke(following: Following): FollowingResponse = with(following) {
                return FollowingResponse(
                    userId = userId.id,
                    createdAt = createdAt,
                )
            }
        }
    }

    companion object {
        operator fun invoke(relation: Relation): FollowResponse = with(relation) {
            return FollowResponse(
                userId = userId.id,
                followings = followings.map { FollowingResponse(it) },
            )
        }
    }
}
