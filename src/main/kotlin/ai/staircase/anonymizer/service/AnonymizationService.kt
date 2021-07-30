package ai.staircase.anonymizer.service

import ai.staircase.anonymizer.service.replacer.Replacer
import org.springframework.stereotype.Service

/**
 * A simple implementation based on regex replacers.
 *
 * Regexes are relatively slow and every replacer recreates the string.
 * The better way would be to implement a tokenizer,
 * process the tokens (potentially in parallel) and rebuild the string from it.
 * But it would take much more time.
 */
@Service
class AnonymizationService(val replacers: List<Replacer>) {

    fun anonymize(text: String) =
        replacers.fold(text) { acc, replacer -> replacer.replace(acc) }
}
