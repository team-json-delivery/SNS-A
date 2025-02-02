package json.delivery.socialnetworkservice

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import json.delivery.socialnetworkservice.cache.EnableCaffeineCache
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableCaffeineCache
@SpringBootApplication
@OpenAPIDefinition(
    info = Info(title = "Social Network Service Api"),
    servers = [
        Server(url = "\${swagger.base-url:http://localhost:8008}"),
    ],
)
class SocialNetworkServiceApplication

fun main(args: Array<String>) {
    runApplication<SocialNetworkServiceApplication>(*args)
}
