package json.delivery.socialnetworkservice.app.domain

import java.time.ZonedDateTime

class Following(
    val userId: UserId,
) {
    val createdAt: ZonedDateTime = ZonedDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Following

        return userId == other.userId
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }
}
