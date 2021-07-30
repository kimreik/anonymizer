package ai.staircase.anonymizer.service.replacer

import ai.staircase.anonymizer.repository.FakeIdRepository
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
@Order(3)
class IdReplacer(fakeIdRepository: FakeIdRepository) :
    RegexReplacer(
        fakeIdRepository,
        Pattern.compile("\\d{4,}")
    )