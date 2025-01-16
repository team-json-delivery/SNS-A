package json.delivery.socialnetworkservice.app.domain.relation

import json.delivery.socialnetworkservice.app.domain.UserId
import java.time.LocalDateTime

class Following(
    val userId: UserId,
) {
    val createdAt: LocalDateTime = LocalDateTime.now()
}
