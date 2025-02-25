package json.delivery.socialnetworkservice.app.api.v1

import json.delivery.socialnetworkservice.app.api.v1.request.FollowRequest
import json.delivery.socialnetworkservice.app.api.v1.request.UnFollowRequest
import json.delivery.socialnetworkservice.app.api.v1.response.FollowResponse
import json.delivery.socialnetworkservice.app.application.RelationUseCase
import json.delivery.socialnetworkservice.app.domain.UserId
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController(
    private val relationUseCase: RelationUseCase,
) {
    @GetMapping("/{userId}")
    fun getFollow(@PathVariable userId: Long): FollowResponse {
        return relationUseCase.follower(UserId(userId))
            .let { FollowResponse(it) }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/follow")
    fun followUser(
        @PathVariable userId: Long,
        @Validated @RequestBody request: FollowRequest,
    ): FollowResponse {
        return relationUseCase.followUser(UserId(userId), UserId(request.followerId))
            .let { FollowResponse(it) }
    }

    @PutMapping("/{userId}/follow")
    fun unFollowUser(
        @PathVariable userId: Long,
        @Validated @RequestBody request: UnFollowRequest,
    ): FollowResponse {
        return relationUseCase.unFollowUser(UserId(userId), UserId(request.unFollowerId))
            .let { FollowResponse(it) }
    }
}
