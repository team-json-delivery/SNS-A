package json.delivery.socialnetworkservice.cache

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.boot.autoconfigure.cache.CacheProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Import(CaffeineCacheConfig::class, CaffeineConfiguration::class)
annotation class EnableCaffeineCache

@Configuration
@EnableCaching
@ComponentScan(basePackageClasses = [CaffeineCacheConfig::class])
class CaffeineCacheConfig(private val caffeineSpecs: CaffeineSpecs) {

    @Bean
    fun caffeineCacheManager(): CaffeineCacheManager? {
        val caffeineCacheManager = CaffeineCacheManager()
        caffeineSpecs.caches.entries.forEach {
            caffeineCacheManager.registerCustomCache(it.key, Caffeine.from(it.value).build())
        }
        return caffeineCacheManager
    }
}

@Configuration
@EnableConfigurationProperties(CacheProperties::class, CaffeineSpecs::class)
class CaffeineConfiguration

@ConfigurationProperties(prefix = "caffeine.spec")
class CaffeineSpecs(
    val caches: Map<String, String>,
)