package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.User

interface UserUseCase {
    suspend fun getUserByUserId(userId: Long): User
    suspend fun registerUser(name: String): User
}
