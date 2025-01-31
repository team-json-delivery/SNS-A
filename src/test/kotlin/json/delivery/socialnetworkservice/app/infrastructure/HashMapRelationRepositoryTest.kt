package json.delivery.socialnetworkservice.app.infrastructure

import com.navercorp.fixturemonkey.kotlin.giveMeKotlinBuilder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import json.delivery.socialnetworkservice.FixtureMonkey
import json.delivery.socialnetworkservice.app.domain.Relation
import json.delivery.socialnetworkservice.app.domain.UserIdMother
import java.util.concurrent.ConcurrentHashMap

@DisplayName("HashMapRelationRepository")
internal class HashMapRelationRepositoryTest : DescribeSpec({

    val userId = UserIdMother.generate()

    describe("save()는") {

        context("Relation이 주어졌을 때") {

            val sut = HashMapRelationRepository()
            val relation = FixtureMonkey.fixture()
                .giveMeKotlinBuilder<Relation>()
                .set("userId", userId)
                .size("_followings", 0)
                .sample()

            it("Relation을 저장한다.") {
                sut.save(relation)

                val actual = sut.findAll()

                actual.shouldContain(relation)
            }
        }
    }

    describe("findByUserId()는") {

        context("Relation이 존재하지 않으면") {

            val sut = HashMapRelationRepository()

            it("IllegalStateException()을 던진다.") {

                val exception = shouldThrow<IllegalStateException> {
                    sut.findByUserId(userId)
                }
                exception.message shouldBe "Relation이 존재하지 않습니다."
            }
        }

        context("Relation이 존재하면") {

            val sut = HashMapRelationRepository(
                ConcurrentHashMap(mutableMapOf(userId to Relation(userId)))
            )

            it("Relation을 리턴한다.") {
                val actual = sut.findByUserId(userId)

                actual.shouldBeInstanceOf<Relation>()
                actual.userId shouldBe userId
            }
        }
    }
},)
