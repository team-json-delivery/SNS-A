package json.delivery.socialnetworkservice.app.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserIdTest {
    @Test
    @DisplayName("UserId should be positive")
    fun test1() {
        assertThrows(IllegalArgumentException::class.java) {
            UserId(-1)
        }

        assertThrows(IllegalArgumentException::class.java) {
            UserId(0)
        }
    }
}
