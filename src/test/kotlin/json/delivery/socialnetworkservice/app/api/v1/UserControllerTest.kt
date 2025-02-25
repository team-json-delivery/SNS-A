package json.delivery.socialnetworkservice.app.api.v1

import com.navercorp.fixturemonkey.kotlin.giveMeKotlinBuilder
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import json.delivery.socialnetworkservice.FixtureMonkey
import json.delivery.socialnetworkservice.app.api.v1.request.FollowRequest
import json.delivery.socialnetworkservice.app.api.v1.request.UnFollowRequest
import json.delivery.socialnetworkservice.app.application.RelationUseCase
import json.delivery.socialnetworkservice.app.domain.Following
import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import net.jqwik.api.Arbitraries
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(UserController::class)
@DisplayName("UserController")
internal class UserControllerTest(
    private val webTestClient: WebTestClient,
    @MockitoBean private val relationUseCase: RelationUseCase,
) : DescribeSpec({

    extensions(SpringExtension)

    describe("[POST] /v1/user/{userId}/follow") {

        context("올바른 요청이 들어오면") {

            val request = FollowRequest(followerId = 3L)
            val relation = Relation(UserId(1L), mutableMapOf(UserId(3L) to Following(UserId(3L))))

            it("201 응답을 리턴한다.") {

                given(relationUseCase.followUser(UserId(1L), UserId(3L)))
                    .willReturn(relation)

                webTestClient.post()
                    .uri("/v1/user/{userId}/follow", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isCreated
            }
        }

        context("유효하지 않은 followerId일 때") {

            val request = FixtureMonkey.fixture()
                .giveMeKotlinBuilder<FollowRequest>()
                .set("followerId", Arbitraries.longs().lessOrEqual(0))
                .sample()

            it("400 응답을 한다.") {
                webTestClient.post()
                    .uri("/v1/user/{userId}/follow", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isBadRequest
            }
        }
    }

    describe("[PUT] /v1/user/{userId}/follow") {

        context("올바른 요청이 들어오면") {

            val request = UnFollowRequest(unFollowerId = 3L)
            val relation = Relation(UserId(1L), mutableMapOf(UserId(3L) to Following(UserId(3L))))

            it("200 응답을 리턴한다.") {

                given(relationUseCase.unFollowUser(UserId(1L), UserId(3L)))
                    .willReturn(relation)

                webTestClient.put()
                    .uri("/v1/user/{userId}/follow", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isOk
            }
        }

        context("유효하지 않은 followerId일 때") {

            val request = FixtureMonkey.fixture()
                .giveMeKotlinBuilder<UnFollowRequest>()
                .set("unFollowerId", Arbitraries.longs().lessOrEqual(0))
                .sample()

            it("400 응답을 한다.") {
                webTestClient.put()
                    .uri("/v1/user/{userId}/follow", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .exchange()
                    .expectStatus().isBadRequest
            }
        }
    }
},)
