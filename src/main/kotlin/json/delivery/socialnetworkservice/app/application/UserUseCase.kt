package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Following
import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.User

interface UserUseCase {
    suspend fun getUserByUserId(userId: Long): User
    suspend fun registerUser(name: String): User

    // TODO check
    suspend fun followUser(userId: Long, followerId: Following): Relation
    suspend fun unFollowUser(userId: Long, followerId: Following): Relation
    suspend fun follower(userId: Long): Relation
}
