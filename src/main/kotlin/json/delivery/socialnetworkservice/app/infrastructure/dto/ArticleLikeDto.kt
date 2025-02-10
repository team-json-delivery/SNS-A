package json.delivery.socialnetworkservice.app.entity

import jakarta.persistence.*

@Entity
@Table(name = "article_likes")
data class ArticleLikeDto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "article_id", nullable = false)
    val articleId: String,

    @Column(name = "user_id", nullable = false)
    val userId: Long
) {
    constructor() : this(0L, "", 0L)
}
