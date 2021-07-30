package ai.staircase.anonymizer.service

import ai.staircase.anonymizer.service.replacer.Replacer
import io.kotlintest.shouldBe
import io.mockk.*
import org.junit.jupiter.api.Test

class AnonymizationServiceTest {

    private val firstReplacer = mockk<Replacer>()
    private val secondReplacer = mockk<Replacer>()

    private val anonymizationService = AnonymizationService(listOf(firstReplacer, secondReplacer))

    @Test
    fun `uses replacers to anonymize`() {
        val origin = "sensitive_data_1 and sensitive_data_2"
        val expected = "faked_1 and faked_2"

        every { firstReplacer.replace(any()) } answers {
            (it.invocation.args[0] as String).replace(
                "sensitive_data_1",
                "faked_1"
            )
        }

        every { secondReplacer.replace(any()) } answers {
            (it.invocation.args[0] as String).replace(
                "sensitive_data_2",
                "faked_2"
            )
        }

        val anonymized = anonymizationService.anonymize(origin)

        anonymized shouldBe expected

        verify { firstReplacer.replace(any()) }
        verify { secondReplacer.replace(any()) }
    }

}