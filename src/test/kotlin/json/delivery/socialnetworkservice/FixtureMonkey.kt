package json.delivery.socialnetworkservice

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.FixtureMonkeyBuilder
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin

object FixtureMonkey {
    private fun fixtureMonkeyBuilder(): FixtureMonkeyBuilder {
        return FixtureMonkey.builder()
            .plugin(KotlinPlugin())
    }

    fun fixture(fixtureMonkeyBuilder: FixtureMonkeyBuilder = fixtureMonkeyBuilder()): FixtureMonkey {
        return fixtureMonkeyBuilder
            .build()
    }
}
