package json.delivery.socialnetworkservice.app.application

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.infrastructure.ArticleRepository

class ArticleServiceTest : DescribeSpec({

    describe("article을 ..") {

        val articleRepository = mockk<ArticleRepository>()
        val articleService = ArticleService(articleRepository)

        val testArticle: Article = Article.create(UserId(3), "test Content")

        coEvery { articleRepository.findById(any()) }.returns(testArticle)

        it("article get") {
            val article = articleService.get(testArticle.id)
            article.id shouldBe testArticle.id
        }

    }

})
