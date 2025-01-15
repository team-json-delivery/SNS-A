package json.delivery.socialnetworkservice.app.application

import json.delivery.socialnetworkservice.app.infrastructure.SampleRepository
import org.springframework.stereotype.Service

@Service
class SampleService(private val sampleRepository: SampleRepository) {

    suspend fun sample() = sampleRepository.sample()
}