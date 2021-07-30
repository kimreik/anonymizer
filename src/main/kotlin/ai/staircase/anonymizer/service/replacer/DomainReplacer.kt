package ai.staircase.anonymizer.service.replacer

import ai.staircase.anonymizer.repository.FakeDomainRepository
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
@Order(2)
class DomainReplacer(val fakeDomainRepository: FakeDomainRepository) :
    RegexReplacer(
        fakeDomainRepository,
        Pattern.compile(
            "(https?://)?(www\\.)?([\\w-]+\\.)+[\u200C\u200B\\w]{2,63}/?",
            Pattern.CASE_INSENSITIVE
        )
    ) {

    override fun replaceToken(token: String): String {
        val domain = getDomainName(token)

        return token.replaceFirst(domain, fakeDomainRepository.getFake(domain))
    }

    private fun getDomainName(url: String): String =
        url
            .trimProtocol()
            .trimPath()
            .trimWWW()
            .trimTopLevelDomain()

    private fun String.trimProtocol(): String =
        if (this.contains("://")) {
            this.split("://")[1]
        } else this

    private fun String.trimWWW(): String =
        removePrefix("www.")

    private fun String.trimTopLevelDomain(): String =
        substring(startIndex = 0, endIndex = lastIndexOf("."))

    private fun String.trimPath(): String =
        split("/")[0]
}

