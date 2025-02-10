package json.delivery.socialnetworkservice.app.application

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.infrastructure.ArticleRepository
import json.delivery.socialnetworkservice.app.infrastructure.RelationRepository

class FeedServiceTest(
    private val articleRepository: ArticleRepository = mockk(),
    private val relationRepository: RelationRepository = mockk(),
    private val feedService:FeedService = FeedService(articleRepository, relationRepository)
): DescribeSpec({

    describe("FeedService Test ") {

        val userId = UserId(1)
        val followedUserIds = listOf(UserId(2), UserId(3))
        val testArticles: List<Article> = listOf(
            Article.create(UserId(2), "test Content"),
            Article.create(UserId(3), "test Content222"),
            Article.create(UserId(4), "test Content444")
        )

        val testRelation = Relation(userId)
        testRelation.following(UserId(2))
        testRelation.following(UserId(3))

        coEvery { articleRepository.findAllByAuthorIds(followedUserIds) } returns testArticles
        coEvery { relationRepository.findByUserId(userId) } returns testRelation

        it("내가 팔로잉 중인 user의 feed만 보인다") {
            val feeds: List<Article> = feedService.feeds(userId)
            feeds.size shouldBe 2
        }
    }

    describe("FeedService Test v2") {
        val userId = UserId(1)
        val followedUserIds = listOf(UserId(2), UserId(3))
        val testArticles: List<Article> = listOf(
            Article.create(UserId(2), "test Content"),
            Article.create(UserId(3), "test Content222"),
            Article.create(UserId(4), "test Content444")
        )

        val testRelation = Relation(userId)

        coEvery { articleRepository.findAllByAuthorIds(followedUserIds) } returns testArticles
        coEvery { articleRepository.findAllByAuthorIds(emptyList()) } returns emptyList()
        coEvery { relationRepository.findByUserId(userId) } returns testRelation


        it("following 0 이면") {
            val feeds: List<Article> = feedService.feeds(userId)
            feeds.size shouldBe 0

            coVerify(exactly = 1) { articleRepository.findAllByAuthorIds(emptyList()) }
        }
    }

})
