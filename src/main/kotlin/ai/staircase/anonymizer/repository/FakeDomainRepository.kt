package ai.staircase.anonymizer.repository

import com.github.javafaker.Faker
import org.springframework.stereotype.Repository

@Repository
class FakeDomainRepository(private val faker: Faker) : FakeDataRepository() {

    override fun buildFake(): String =
        faker.internet().domainWord()
}