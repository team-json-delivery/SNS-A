package json.delivery.socialnetworkservice.app.infrastructure

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import json.delivery.socialnetworkservice.app.domain.Article
import json.delivery.socialnetworkservice.app.domain.UserId
import json.delivery.socialnetworkservice.app.exception.ArticleNotFoundException
import org.junit.jupiter.api.Assertions.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
internal class MysqlArticleRepositoryTest @Autowired constructor(
    private val articleJpaClient: ArticleJpaClient,
    private val articleLikeJpaClient: ArticleLikeJpaClient
) : DescribeSpec({
    val repository = MysqlArticleRepository(articleJpaClient, articleLikeJpaClient)

    describe("MysqlArticleRepository") {

        context("when saving an article") {
            it("should save and retrieve the article correctly") {
                // Given: 새로운 Article 생성
                val article = Article.create(UserId(1L), "Content")

                // When: 저장
                repository.save(article)

                // Then: 정상 조회 확인
                val retrievedArticle = repository.findById(article.id)
                retrievedArticle.id shouldBe article.id
            }
        }

        context("when finding a non-existent article") {
            it("should throw an exception") {
                // When & Then: 존재하지 않는 ID 조회 시 예외 발생 확인
                val expected = assertThrows(ArticleNotFoundException::class.java) {
                    repository.findById("non-existent-id")
                }
                expected.message shouldBe "Article not found : non-existent-id"
            }
        }

        context("if there is likes, when saving an article") {
            it("should save and retrieve the article correctly") {
                // Given: 새로운 Article 생성
                val article = Article.create(UserId(1L), "Content")
                article.addLike(UserId(2L))
                article.addLike(UserId(3L))

                // When: 저장
                repository.save(article)

                // Then: 정상 조회 확인
                val retrievedArticle = repository.findById(article.id)
                retrievedArticle.id shouldBe article.id
                retrievedArticle.likes shouldBe article.likes
            }
        }
    }
}) {
    companion object {
        @Container
        val mysqlContainer = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("test_social_network")
            withUsername("testuser")
            withPassword("testpassword")
            withReuse(true) // 컨테이너 재사용 가능
            start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun configureDataSource(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { mysqlContainer.jdbcUrl }
            registry.add("spring.datasource.username") { mysqlContainer.username }
            registry.add("spring.datasource.password") { mysqlContainer.password }
            registry.add("spring.datasource.driver-class-name") { "com.mysql.cj.jdbc.Driver" }
            registry.add("spring.jpa.properties.hibernate.dialect") { "org.hibernate.dialect.MySQL8Dialect" }
        }
    }
}
