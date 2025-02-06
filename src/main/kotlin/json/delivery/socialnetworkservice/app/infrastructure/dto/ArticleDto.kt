package json.delivery.socialnetworkservice.app.entity

import jakarta.persistence.*

@Entity
@Table(name = "articles")
data class ArticleDto(
    @Id
    val id: String,

    @Column(name = "author_id", nullable = false)
    val authorId: Long,

    @Column(nullable = false)
    val content: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @Column(name = "updated_at", nullable = false)
    val updatedAt: Long
) {
    constructor() : this("", 0L, "", 0L, 0L)
}
