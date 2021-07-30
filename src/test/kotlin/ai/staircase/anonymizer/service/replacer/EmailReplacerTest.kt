package ai.staircase.anonymizer.service.replacer

import ai.staircase.anonymizer.repository.FakeEmailRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class EmailReplacerTest {

    private val fakeEmailRepository = mockk<FakeEmailRepository>()

    private val emailReplacer = EmailReplacer(fakeEmailRepository)

    private val originEmail = "email@gmail.com"
    private val fakeEmail = "fake@gmail.com"

    init {
        every { fakeEmailRepository.getFake(originEmail) } returns fakeEmail
    }

    @Test
    fun `does nothing if no emails in text`() {
        val origin = "text without email"
        val replaced = emailReplacer.replace(origin)
        replaced shouldBe origin
    }

    @Test
    fun `replaces email with the fake one`() {
        val origin = "text with email: $originEmail"
        val expected = "text with email: $fakeEmail"

        val replaced = emailReplacer.replace(origin)

        replaced shouldBe expected
        verify { fakeEmailRepository.getFake(originEmail) }
    }

    @Test
    fun `email surrounded by special characters`() {
        val origin = "from: <$originEmail>"
        val expected = "from: <$fakeEmail>"

        val replaced = emailReplacer.replace(origin)

        replaced shouldBe expected
        verify { fakeEmailRepository.getFake(originEmail) }
    }
}