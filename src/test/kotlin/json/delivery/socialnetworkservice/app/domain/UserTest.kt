package json.delivery.socialnetworkservice.app.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserTest : DescribeSpec({
    describe("User Test") {
        it("should be created successfully") {
            val userName = "John"
            val userId = UserId(1)
            val user = User(id = userId, name = userName)
            user.name shouldBe "John"
            user.id.id shouldBe UserId(1).id
        }

        it("user name should not be empty") {
            shouldThrow<IllegalArgumentException> {
                User(id = UserId(1), name = "")
            }
        }
    }
})
