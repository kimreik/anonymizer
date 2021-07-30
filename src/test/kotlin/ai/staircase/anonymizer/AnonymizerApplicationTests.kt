package ai.staircase.anonymizer

import ai.staircase.anonymizer.controller.AnonymizationController
import ai.staircase.anonymizer.model.TextWrapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AnonymizerApplicationTests(
    @Autowired private val controller: AnonymizationController
) {

    @Test
    fun contextLoads() {
    }

    /**
     * It's just an example of the service logic.
     * So, we don't mock or assert anything here
     */
    @Test
    fun example() {
        val text = TextWrapper(
            """
    from:       Roy L. Johnson <RoyLJohnson@fake.com>
    to:	        Cindy S. Brown <CindySBrown@my-awesome-domain.com>
    subject:    An issue with my-awesome-domain.com
                        
            Hello. I'm writing you about the issue I faced at your platform my-awesome-domain.com
            I see "Unexpected server error" status on my transaction #3585732782
            My user id is 8110474
                
            Attaching the link just in case:
            https://my-awesome-domain.com/transactions/3585732782
                            
            ======================================================================================
            Roy L. Johnson| sales manager | fake.com | RoyLJohnson@fake.com
            """.trimIndent()
        )

        println("origin:\n\n\n${text.text}\n\n\n")
        println("anonymized:\n\n\n${controller.anonymize(text)}")
    }
}
