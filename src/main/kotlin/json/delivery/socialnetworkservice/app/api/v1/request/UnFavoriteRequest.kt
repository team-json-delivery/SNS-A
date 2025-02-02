package json.delivery.socialnetworkservice.app.api.v1.request

import jakarta.validation.constraints.Positive

data class UnFavoriteRequest(
    @Positive
    val userId: Long,
)
