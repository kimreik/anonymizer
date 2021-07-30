package ai.staircase.anonymizer.service.replacer

import ai.staircase.anonymizer.repository.FakeEmailRepository
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
@Order(1)
class EmailReplacer(fakeEmailRepository: FakeEmailRepository) :
    RegexReplacer(
        fakeEmailRepository,
        Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE)
    )
