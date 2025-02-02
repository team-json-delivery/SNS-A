package json.delivery.socialnetworkservice.app.domain
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.giveMeOne
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import json.delivery.socialnetworkservice.FixtureMonkey
import net.jqwik.api.Arbitraries

@DisplayName("Relation")
class RelationTest : DescribeSpec({

    describe("following()은") {

        context("followingId가 주어졌을 때") {

            val relation = Relation(UserId(1))
            val followingId = UserIdMother.generate()

            it("followingId를 추가한다.") {
                relation.following(followingId)

                relation.followings shouldHaveSize 1
                relation.followings.first().userId shouldBe followingId
            }
        }
    }

    describe("unFollowing()은") {

        context("followingId가 주어졌을 때") {

            val followingId = UserIdMother.generate()
            val relation = Relation(UserId(1L), mutableMapOf(followingId to Following(followingId)))

            it("followingId를 제거한다.") {
                relation.unFollowing(followingId)

                relation.followings.shouldBeEmpty()
            }
        }
    }
})
