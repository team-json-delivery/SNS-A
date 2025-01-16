package json.delivery.socialnetworkservice.app.domain.relation

import json.delivery.socialnetworkservice.app.domain.UserId
import java.time.LocalDateTime

class Relation(
    val userId: UserId,
) {
    // 내가 구독하는 사람
    private val _followings: MutableMap<UserId, Following> = mutableMapOf()
    val followings: List<Following>
        get() = _followings.values.toList()

    val createdAt: LocalDateTime = LocalDateTime.now()
    var updatedAt: LocalDateTime = LocalDateTime.now()

    fun following(followingId: UserId) {
        _followings[followingId] = Following(followingId)
        updatedAt = LocalDateTime.now()
    }

    fun unFollowing(followingId: UserId) {
        _followings.remove(followingId)
        updatedAt = LocalDateTime.now()
    }
}

