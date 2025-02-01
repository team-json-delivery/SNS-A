package json.delivery.socialnetworkservice.app.infrastructure

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.exception.ArticleNotFoundException
import org.junit.jupiter.api.Assertions.assertThrows

internal class HashMapArticleRepositoryTest : DescribeSpec({
    describe("HashMapArticleRepository") {
        val repository = HashMapArticleRepository()

        context("when saving an article") {
            it("should save and retrieve the article correctly") {
                val article = Article.create(UserId(1L), "Content")
                repository.save(article)
                val retrievedArticle = repository.findById(article.id)
                retrievedArticle shouldBe article
            }
        }

        context("when finding a non-existent article") {
            it("should return null") {
                val expected =
                    assertThrows(ArticleNotFoundException::class.java) { repository.findById("non-existent-id") }
                expected.message shouldBe "Article not found : non-existent-id"
            }
        }
    }
})
