package json.delivery.socialnetworkservice.app.api.v1.request

import jakarta.validation.constraints.Positive

data class UnFollowRequest(
    @field:Positive
    val unFollowerId: Long,
)
