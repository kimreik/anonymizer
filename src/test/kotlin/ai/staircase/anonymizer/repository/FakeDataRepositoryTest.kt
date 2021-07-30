package ai.staircase.anonymizer.repository

import com.github.javafaker.Faker
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class FakeDataRepositoryTest {

    private val faker = mockk<Faker>()

    private val repository = object : FakeDataRepository() {
        override fun buildFake(): String = faker.number().digits(10)
    }

    @Test
    fun `caches the result`(){
        val expected = "1234567890"
        every { faker.number().digits(10) } returns expected

        val first = repository.getFake("key")
        val second = repository.getFake("key")

        first shouldBe expected
        second shouldBe expected

        verify(exactly = 1) { faker.number() }

    }
}