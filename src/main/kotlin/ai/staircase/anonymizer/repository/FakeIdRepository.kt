package ai.staircase.anonymizer.repository

import com.github.javafaker.Faker
import org.springframework.stereotype.Repository

@Repository
class FakeIdRepository(private val faker: Faker) : FakeDataRepository() {

    override fun buildFake(): String =
        faker.number().digits(7)
}