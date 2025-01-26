package json.delivery.socialnetworkservice.app.domain

import com.navercorp.fixturemonkey.kotlin.giveMeKotlinBuilder
import json.delivery.socialnetworkservice.FixtureMonkey
import net.jqwik.api.Arbitraries

object UserIdMother {

    fun generate(userId: Long? = null): UserId {
        val userFixture = FixtureMonkey.fixture()
            .giveMeKotlinBuilder<UserId>()

        if (userId == null) {
            return userFixture
                .set("id", Arbitraries.longs().greaterOrEqual(1))
                .sample()
        }

        return userFixture
            .set("id", userId)
            .sample()
    }
}
