package json.delivery.socialnetworkservice.app.domain.relation

import json.delivery.socialnetworkservice.app.domain.UserId
import java.time.ZonedDateTime

class Following(
    val userId: UserId,
) {
    val createdAt: ZonedDateTime = ZonedDateTime.now()
}
