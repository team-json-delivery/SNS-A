package json.delivery.socialnetworkservice.app.application

import com.navercorp.fixturemonkey.customizer.InnerSpec
import com.navercorp.fixturemonkey.kotlin.giveMeKotlinBuilder
import com.navercorp.fixturemonkey.kotlin.set
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import json.delivery.socialnetworkservice.FixtureMonkey
import json.delivery.socialnetworkservice.app.domain.Following
import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.domain.UserIdMother
import json.delivery.socialnetworkservice.app.infrastructure.RelationRepository
import net.jqwik.api.Arbitraries

@DisplayName("RelationService")
internal class RelationServiceTest(
    private val relationRepository: RelationRepository = mockk(),
    private val relationService: RelationService = RelationService(relationRepository),
) : DescribeSpec({

    describe("followUser()는") {

        context("userId와 followerId가 주어졌을 때") {

            val userId = UserIdMother.generate()
            val followerId = UserIdMother.generate()
            val relation = FixtureMonkey.fixture()
                .giveMeKotlinBuilder<Relation>()
                .set("userId", userId)
                .size("_followings", 0)
                .sample()

            it("팔로잉 목록에 followerId를 추가한다.") {
                every { relationRepository.findByUserId(userId) } returns relation
                every { relationRepository.save(relation) } answers {}

                val actual = relationService.followUser(userId, followerId)

                actual.followings.shouldContain(Following(followerId))
                verify(exactly = 1) { relationRepository.save(relation) }
            }
        }
    }

    describe("unFollowUser()는") {

        context("userId와 unFollowerId가 주어졌을 때") {

            val userId = UserIdMother.generate()
            val unFollowerId = UserIdMother.generate()
            val relation = FixtureMonkey.fixture()
                .giveMeKotlinBuilder<Relation>()
                .set("userId", userId)
                .size("_followings", 1)
                .setInner(InnerSpec()
                    .property("_followings") { it.entry(unFollowerId, Following(unFollowerId)) })
                .sample()

            it("팔로잉 목록에서 제거한다.") {
                every { relationRepository.findByUserId(userId) } returns relation
                every { relationRepository.save(relation) } answers {}

                val actual = relationService.unFollowUser(userId, unFollowerId)

                actual.followings.shouldNotContain(Following(unFollowerId))
                verify(exactly = 1) { relationRepository.save(relation) }
            }
        }
    }
},)
