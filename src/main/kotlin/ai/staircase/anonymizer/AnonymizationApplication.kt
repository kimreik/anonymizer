package ai.staircase.anonymizer

import com.github.javafaker.Faker
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AnonymizationApplication {

    @Bean
    fun faker() = Faker()
}

fun main(args: Array<String>) {
    runApplication<AnonymizationApplication>(*args)
}


