package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.User
import json.delivery.socialnetworkservice.app.domain.UserId

interface UserUseCase {
    suspend fun getUserByUserId(userId: Long): User
    suspend fun registerUser(name: String): User

    // TODO check
    suspend fun followUser(userId: UserId, followerId: UserId): Relation
    suspend fun unFollowUser(userId: UserId, followerId: UserId): Relation
    suspend fun follower(userId: UserId): Relation
}
