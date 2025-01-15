package json.delivery.socialnetworkservice

import json.delivery.socialnetworkservice.cache.EnableCaffeineCache
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableCaffeineCache
@SpringBootApplication
class SocialNetworkServiceApplication

fun main(args: Array<String>) {
    runApplication<SocialNetworkServiceApplication>(*args)
}
