package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId

interface RelationUseCase {
    fun followUser(userId: UserId, followerId: UserId): Relation
    fun unFollowUser(userId: UserId, followerId: UserId): Relation
    fun follower(userId: UserId): Relation
}
