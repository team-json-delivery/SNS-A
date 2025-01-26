package json.delivery.socialnetworkservice.app.api.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.navercorp.fixturemonkey.kotlin.giveMeKotlinBuilder
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import json.delivery.socialnetworkservice.FixtureMonkey
import json.delivery.socialnetworkservice.app.api.v1.request.FollowRequest
import json.delivery.socialnetworkservice.app.application.RelationUseCase
import json.delivery.socialnetworkservice.app.domain.Following
import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.mockito.kotlin.eq
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
    }
},)