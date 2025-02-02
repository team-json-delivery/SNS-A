package json.delivery.socialnetworkservice.app.api.v1

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import json.delivery.socialnetworkservice.app.api.v1.request.FavoriteRequest
import json.delivery.socialnetworkservice.app.application.UnFavoriteUsecase
import json.delivery.socialnetworkservice.app.application.dto.UnFavoriteUsecaseOutputDto
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.exception.ArticleNotFoundException
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(UnFavoriteController::class)
@DisplayName("UnFavoriteController")
internal class  UnFavoriteControllerTest(
    private val webTestClient: WebTestClient,
    @MockitoBean private val unFavoriteUsecase: UnFavoriteUsecase,
) : DescribeSpec({

    extensions(SpringExtension)

    describe("[PUT] /v1/article/{articleId}/favorite") {

        context("올바른 요청이 들어오면") {

            val request = FavoriteRequest(userId = 3L)
            val expected = UnFavoriteUsecaseOutputDto(UserId(3L), "articleId")
            it("200 응답을 리턴한다.") {

                given(unFavoriteUsecase.execute(UserId(3L), "articleId"))
                    .willReturn(expected)

                webTestClient.put()
                    .uri("/v1/article/{articleId}/favorite", "articleId")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isOk
                    .expectBody()
                    .json(
                        """
                        {
                            "userId": 3,
                            "articleId": "articleId"
                        }
                        """.trimIndent()
                    )
            }
        }

        context("올바르지 않은 요청이 들어오면") {

            val request = FavoriteRequest(userId = 3L)
            it("404 응답을 리턴한다.") {

                given(unFavoriteUsecase.execute(UserId(3L), "articleId"))
                    .willThrow(ArticleNotFoundException("articleId"))

                webTestClient.put()
                    .uri("/v1/article/{articleId}/favorite", "articleId")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isNotFound
            }
        }

        context("userId가 음수이면 에러가 발생한다") {

            val request = FavoriteRequest(userId = -1L)
            it("400 응답을 리턴한다.") {

                webTestClient.put()
                    .uri("/v1/article/{articleId}/favorite", "articleId")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isBadRequest
            }
        }
    }
},)
