package json.delivery.socialnetworkservice.app.domain

import java.util.*

class Article private constructor(
    val id: String,
    val authorId: UserId,
    val createdAt: Long,
    val likes: MutableList<UserId>,
    private var content: String,
    private var updatedAt: Long
) {
    fun addLike(userId: UserId) {
        likes.add(userId)
        updatedAt = System.currentTimeMillis()
    }

    fun hasLike(userId: UserId): Boolean {
        return likes.contains(userId)
    }

    fun updateContent(updatedContent: String) {
        content = updatedContent
        updatedAt = System.currentTimeMillis()
    }

    fun getContent(): String {
        return content;
    }

    fun getUpdatedAt(): Long {
        return updatedAt;
    }

    fun disLike(userId: UserId) {
        likes.remove(userId)
        updatedAt = System.currentTimeMillis()
    }

    init {
        require(content.isNotBlank()) { "Content must not be blank." }
    }

    companion object {
        fun create(
            authorId: UserId,
            content: String,
        ): Article {
            return Article(
                id = UUID.randomUUID().toString(),
                content = content,
                authorId = authorId,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis(),
                likes = arrayListOf()
            )
        }
        fun of(
            id: String,
            authorId: Long,
            content: String,
            createdAt: Long,
            updatedAt: Long,
            likes: List<Long>
        ): Article {
            return Article(
                id = id,
                authorId = UserId(authorId),
                createdAt = createdAt,
                updatedAt = updatedAt,
                content = content,
                likes = likes.map { UserId(it) }.toMutableList()
            )
        }
    }
}
