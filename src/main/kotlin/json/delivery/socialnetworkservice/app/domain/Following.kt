package json.delivery.socialnetworkservice.app.domain

import java.time.ZonedDateTime

class Following(
    val userId: UserId,
) {
    val createdAt: ZonedDateTime = ZonedDateTime.now()
}
