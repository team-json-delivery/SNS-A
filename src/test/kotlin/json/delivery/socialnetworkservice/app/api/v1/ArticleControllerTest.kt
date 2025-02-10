package json.delivery.socialnetworkservice.app.api.v1

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import json.delivery.socialnetworkservice.app.application.ArticleService
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import org.mockito.BDDMockito.given
import org.mockito.kotlin.any
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(ArticleController::class)
class ArticleControllerTest(
    private val webTestClient: WebTestClient,
    @MockitoBean private val articleService: ArticleService,
) : DescribeSpec({

    extensions(SpringExtension)

    describe("GET /v1/article/{articleId}") {

        context("정상 응답") {
            it("게시글 가져오기") {
                val articleId = "12345"
                val testArticle = Article.create(UserId(1), "Test Content")

                given(articleService.get(articleId)).willReturn(testArticle)

                webTestClient.get()
                    .uri("/v1/article/{articleId}", articleId)
                    .exchange()
                    .expectStatus().isOk
                    .expectBody()
                    .jsonPath("$.content").isEqualTo("Test Content")
            }
        }
    }

    describe("POST /v1/article") {

        context("정상 응답") {
            it("게시글 등록 성공") {
                val request = mapOf(
                    "authorId" to 1L,
                    "content" to "Test Content"
                )
                val article = Article.create(UserId(1), "Test Content")

                given(articleService.register(any())).willReturn(article)

                webTestClient.post()
                    .uri("/v1/article")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isCreated
            }
        }

        context("4xx 응답") {
            it("400: 필수 필드 누락") {
                val invalidRequest = mapOf(
                    "authorId" to 1L,
                    "content" to "" // 빈 문자열
                )

                webTestClient.post()
                    .uri("/v1/article")
                    .bodyValue(invalidRequest)
                    .exchange()
                    .expectStatus().isBadRequest
            }
        }
    }
})
