package json.delivery.socialnetworkservice.app.api.v1.request

import jakarta.validation.constraints.Positive

data class FollowRequest(
    @Positive
    val followerId: Long,
)
