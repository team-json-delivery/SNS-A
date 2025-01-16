package json.delivery.socialnetworkservice.app.domain

import java.util.*

class ArticleMother {
    companion object {
        fun generate(
            id: String? = UUID.randomUUID().toString(),
            authorId: UserId = UserId(1L),
            content: String = "Default content",
            updatedAt: Long = System.currentTimeMillis(),
            likes: ArrayList<UserId> = arrayListOf()
        ): Article {
            val article = Article.create(
                authorId = authorId,
                content = content
            )

            setPrivateField(article, "id", id)
            setPrivateField(article, "updatedAt", updatedAt)
            setPrivateField(article, "likes", likes)

            return article
        }

        private fun setPrivateField(instance: Any, fieldName: String, value: Any?) {
            val field = instance::class.java.getDeclaredField(fieldName)
            field.isAccessible = true
            field.set(instance, value)
        }
    }
}