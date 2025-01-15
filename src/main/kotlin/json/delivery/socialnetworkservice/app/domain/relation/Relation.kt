package json.delivery.socialnetworkservice.app.domain.relation

import json.delivery.socialnetworkservice.app.domain.UserId
import java.time.LocalDateTime

class Relation(
    val relationNo: Long,
    val userId: UserId,
    val friendUserId: UserId,
    val status: RelationStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
