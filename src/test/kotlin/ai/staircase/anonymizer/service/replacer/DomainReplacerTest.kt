package ai.staircase.anonymizer.service.replacer

import ai.staircase.anonymizer.repository.FakeDomainRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class DomainReplacerTest {

    private val fakeDomainRepository = mockk<FakeDomainRepository>()

    private val domainReplacer = DomainReplacer(fakeDomainRepository)

    @Test
    fun `does nothing if no URL in text`() {
        val origin = "text without URL"
        val replaced = domainReplacer.replace(origin)
        replaced shouldBe origin
    }

    @Test
    fun `replaces domain with the fake one`() =
        test(
            origin = "google",
            originText = "text with URL: http://google.com",
            expectedText = "text with URL: http://fake.com",
        )

    @Test
    fun `url with protocol www and port`() =
        test(
            origin = "google",
            originText = "text with URL: http://www.google.com:80/path?params",
            expectedText = "text with URL: http://www.fake.com:80/path?params"
        )

    @Test
    fun `url with protocol and www`() =
        test(
            origin = "google",
            originText = "text with URL: http://www.google.com/path?params",
            expectedText = "text with URL: http://www.fake.com/path?params"
        )

    @Test
    fun `url with www`() =
        test(
            origin = "google",
            originText = "text with URL: www.google.com/path?params",
            expectedText = "text with URL: www.fake.com/path?params"
        )

    @Test
    fun `url with https`() =
        test(
            origin = "google",
            originText = "text with URL: https://google.com/path?params",
            expectedText = "text with URL: https://fake.com/path?params"
        )

    @Test
    fun `url without anything else`() =
        test(
            origin = "google",
            originText = "text with URL: google.com",
            expectedText = "text with URL: fake.com"
        )

    @Test
    fun `url with dashes`() =
        test(
            origin = "g-o-o-g-l-e",
            originText = "text with URL: g-o-o-g-l-e.com",
            expectedText = "text with URL: fake.com"
        )

    @Test
    fun `multiple words domain`() =
        test(
            origin = "multiple.words.domain",
            originText = "text with URL: http://multiple.words.domain.com",
            expectedText = "text with URL: http://fake.com",
        )

    @Test
    fun `replaces only domain name`() =
        test(
            origin = "google",
            originText = "text with URL: http://google.com/google",
            expectedText = "text with URL: http://fake.com/google",
        )

    @Test
    fun `multiple urls`() {
        val firstOrigin = "first"
        val secondOrigin = "second"
        val firstFake = "first-fake"
        val secondFake = "second-fake"

        val origin = "text with 2 urls: $firstOrigin.com and http://$secondOrigin.com"
        val expected = "text with 2 urls: $firstFake.com and http://$secondFake.com"

        every { fakeDomainRepository.getFake(firstOrigin) } returns firstFake
        every { fakeDomainRepository.getFake(secondOrigin) } returns secondFake

        val replaced = domainReplacer.replace(origin)

        replaced shouldBe expected

        verify { fakeDomainRepository.getFake(firstOrigin) }
        verify { fakeDomainRepository.getFake(secondOrigin) }
    }

    private fun test(
        origin: String,
        fake: String = "fake",
        originText: String,
        expectedText: String
    ) {
        every { fakeDomainRepository.getFake(origin) } returns fake

        val replaced = domainReplacer.replace(originText)

        replaced shouldBe expectedText

        verify { fakeDomainRepository.getFake(origin) }
    }

}