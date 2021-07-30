package ai.staircase.anonymizer.service.replacer

import ai.staircase.anonymizer.repository.FakeIdRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class IdReplacerTest {

    private val fakeIdRepository = mockk<FakeIdRepository>()

    private val idReplacer = IdReplacer(fakeIdRepository)

    private val originId = "1234"
    private val fakeId = "fake_id"

    init {
        every { fakeIdRepository.getFake(originId) } returns fakeId
    }

    @Test
    fun `does nothing if no ids in text`() = runBlocking {
        val origin = "text without id"
        val replaced = idReplacer.replace(origin)
        replaced shouldBe origin
    }


    @Test
    fun `replaces id with the fake one`() = runBlocking {
        val origin = "text with id: $originId"
        val expected = "text with id: $fakeId"

        val replaced = idReplacer.replace(origin)

        replaced shouldBe expected
        verify { fakeIdRepository.getFake(originId) }

    }

    @Test
    fun `replaces only numbers longer that 3 digits`() = runBlocking {
        val origin = "id: $originId, not id: 123"
        val expected =  "id: $fakeId, not id: 123"

        val replaced = idReplacer.replace(origin)

        replaced shouldBe expected
        verify { fakeIdRepository.getFake(originId) }

    }





}