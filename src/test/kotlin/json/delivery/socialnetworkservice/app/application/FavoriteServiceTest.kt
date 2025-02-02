package json.delivery.socialnetworkservice.app.application

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.infrastructure.ArticleRepository

@DisplayName("FavoriteService")
internal class FavoriteServiceTest(
    private val articleRepository: ArticleRepository = mockk(),
    private val favoriteService: FavoriteService = FavoriteService(articleRepository),
) : DescribeSpec(
    {

        describe("execute()는") {

            context("request가 올바르면 ") {

                val userId = UserId(1L)
                val articleId = "articleId"
                val article = Article.create(userId, "content")
                every { articleRepository.findById(any()) } returns article


                it("article에 userId를 업데이트 하고 받은 userId와 articleId를 반환한다.") {
                    val capturedArticle = slot<Article>()
                    article.addLike(userId)
                    every { articleRepository.save(capture(capturedArticle)) } returns article

                    val expected = favoriteService.execute(userId, articleId)

                    expected.articleId shouldBe articleId
                    expected.userId shouldBe userId
                    val hasLike = capturedArticle.captured.hasLike(userId)
                    hasLike shouldBe true
                }

                it("articleRepository.findById()가 한번 호출된다.") {
                    verify(exactly = 1) { articleRepository.findById(any()) }
                }

                it("articleRepository.save()가 한번 호출된다.") {

                    verify(exactly = 1) { articleRepository.save(any()) }
                }
            }
        }
    },
)
