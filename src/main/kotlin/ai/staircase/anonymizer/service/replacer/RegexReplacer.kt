package ai.staircase.anonymizer.service.replacer

import ai.staircase.anonymizer.repository.FakeDataRepository
import java.util.regex.Pattern

abstract class RegexReplacer(
    private val repository: FakeDataRepository,
    private val pattern: Pattern
) : Replacer {

    override fun replace(text: String): String =
        pattern.matcher(text)
            .replaceAll { matchResult -> replaceToken(matchResult.group()) }

    protected open fun replaceToken(token: String): String =
        repository.getFake(token)
}