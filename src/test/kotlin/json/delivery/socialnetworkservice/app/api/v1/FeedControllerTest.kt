package json.delivery.socialnetworkservice.app.api.v1

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import json.delivery.socialnetworkservice.app.application.FeedUseCase
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(FeedController::class)
class FeedControllerTest(
    private val webTestClient: WebTestClient,
    @MockitoBean private val feedUseCase: FeedUseCase,
): DescribeSpec({

    extensions(SpringExtension)

    describe("GET /v1/feed/{userId}") {

        context("정상 응답") {
            it("나의 목록은..") {
                val userId = UserId(1)
                val testFeeds: List<Article> = listOf(
                    Article.create(UserId(2), "Test Content 1"),
                    Article.create(UserId(3), "Test Content 2")
                )

                given(feedUseCase.feeds(userId)).willReturn(testFeeds)

                webTestClient.get()
                    .uri("/v1/feed/{userId}", userId.id)
                    .exchange()
                    .expectStatus().isOk
                    .expectBody()
                    .jsonPath("$.[0].content").isEqualTo("Test Content 1")
                    .jsonPath("$.length()").isEqualTo(2)
            }
        }

        context("4xx 응답") {
            it("400: userId가 a") {
                webTestClient.get()
                    .uri("/v1/feed/a", "a")
                    .exchange()
                    .expectStatus().isBadRequest
            }
        }
    }
})
