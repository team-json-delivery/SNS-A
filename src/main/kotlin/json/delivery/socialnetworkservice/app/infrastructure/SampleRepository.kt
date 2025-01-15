package json.delivery.socialnetworkservice.app.infrastructure

import org.springframework.stereotype.Component

@Component
class SampleRepository {
    suspend fun sample(): String = "Hello, world!"
}