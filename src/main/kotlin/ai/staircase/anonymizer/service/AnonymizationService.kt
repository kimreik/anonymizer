package ai.staircase.anonymizer.service

import ai.staircase.anonymizer.service.replacer.Replacer
import org.springframework.stereotype.Service

@Service
class AnonymizationService(val replacers: List<Replacer>) {

    fun anonymize(text: String) =
        replacers.fold(text) { acc, replacer -> replacer.replace(acc) }
}
