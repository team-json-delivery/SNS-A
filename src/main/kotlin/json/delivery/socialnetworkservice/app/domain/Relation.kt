package json.delivery.socialnetworkservice.app.domain

import java.time.ZonedDateTime

class Relation(
    val userId: UserId,
    private val _followings: MutableMap<UserId, Following> = mutableMapOf(),
) {
    // 내가 구독하는 사람
    val followings: List<Following>
        get() = _followings.values.toList()

    val createdAt: ZonedDateTime = ZonedDateTime.now()
    var updatedAt: ZonedDateTime = ZonedDateTime.now()

    fun following(followingId: UserId) {
        _followings[followingId] = Following(followingId)
        updatedAt = ZonedDateTime.now()
    }

    fun unFollowing(followingId: UserId) {
        _followings.remove(followingId)
        updatedAt = ZonedDateTime.now()
    }
}

