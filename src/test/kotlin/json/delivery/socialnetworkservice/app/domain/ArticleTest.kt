package json.delivery.socialnetworkservice.app.domain

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ArticleTest {

    val defaultAuthor = UserId(12345L)

    @Test
    @DisplayName("creation test")
    fun test1() {
        val article = Article.create(defaultAuthor, "content")

        assertThat(article.id).isNotNull()
        assertThat(article.getContent()).isEqualTo("content")
        assertThat(article.authorId).isEqualTo(UserId(12345L))
        assertThat(article.createdAt).isNotNull()
        assertThat(article.getUpdatedAt()).isNotNull()
    }

    @Test
    @DisplayName("add new like test")
    fun test2() {
        val article = Article.create(defaultAuthor, "content")
        val likesUser = UserId(54321L)
        article.addLike(likesUser)

        assertThat(article.hasLike(likesUser)).isTrue()
    }

    @Test
    @DisplayName("If content is blank, throw exception")
    fun test3() {
        val throws = assertThrows<IllegalArgumentException> {
            Article.create(defaultAuthor, "")
        }

        assertThat(throws.message).isEqualTo("Content must not be blank.")
    }

    @Test
    @DisplayName("Content is modifiable")
    fun test4() {
        val article = Article.create(defaultAuthor, "content")
        article.updateContent("new content")

        assertThat(article.getContent()).isEqualTo("new content")
    }

    @Test
    @DisplayName("Add like should update updatedAt")
    fun test5() {
        var longLongAgo = System.currentTimeMillis() - 1000000
        val article = ArticleMother.generate(updatedAt = longLongAgo)
        val expected = article.getUpdatedAt()

        article.addLike(UserId(54321L))
        val actual = article.getUpdatedAt()

        assertThat(actual).isGreaterThan(expected)
    }

    @Test
    @DisplayName("Update content should update updatedAt")
    fun test6() {
        var longLongAgo = System.currentTimeMillis() - 1000000
        val article = ArticleMother.generate(updatedAt = longLongAgo)
        val expected = article.getUpdatedAt()

        article.updateContent("new content")
        val actual = article.getUpdatedAt()

        assertThat(actual).isGreaterThan(expected)
    }
}