package json.delivery.socialnetworkservice.app.api

import io.swagger.v3.oas.annotations.tags.Tag
import json.delivery.socialnetworkservice.app.application.SampleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "1. Sample", description = "1. Sample Api")
@RestController
@RequestMapping("/api/v1/sample")
class SampleController(private val sampleService: SampleService) {

    @GetMapping
    suspend fun sample(): ResponseEntity<String> = ResponseEntity.ok(sampleService.sample())
}
