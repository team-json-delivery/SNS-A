package json.delivery.socialnetworkservice.app.domain
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@DisplayName("Relation")
class RelationTest : DescribeSpec({

    describe("following()은") {

        context("followingId가 주어졌을 때") {

            val relation = Relation()
            val followingId = UserId(2L)

            it("followingId를 추가한다.") {
                relation.following(followingId)

                relation.followings shouldHaveSize 1
                relation.followings.first().userId shouldBe followingId
            }
        }
    }

    describe("unFollowing()은") {

        context("followingId가 주어졌을 때") {

            val relation = Relation()
            val followingId = UserId(2L)
            relation.following(followingId)

            it("followingId를 제거한다.") {
                relation.unFollowing(followingId)

                relation.followings.shouldBeEmpty()
            }
        }
    }
})
