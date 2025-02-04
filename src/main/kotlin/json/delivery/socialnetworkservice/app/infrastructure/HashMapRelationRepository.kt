package json.delivery.socialnetworkservice.app.infrastructure

import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Repository
class HashMapRelationRepository(
    private val data: ConcurrentMap<UserId, Relation> = ConcurrentHashMap()
) : RelationRepository {

    override fun save(relation: Relation) {
        // TODO relation를 덮어쓰는 구조라서 relation.followings 동시성 문제 존재
        data[relation.userId] = relation
    }

    override fun findAll(): List<Relation> {
        return data.map { it.value }
    }

    override fun findByUserId(userId: UserId): Relation? {
        return data[userId]
    }
}
