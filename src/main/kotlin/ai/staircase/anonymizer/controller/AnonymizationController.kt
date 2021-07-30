package ai.staircase.anonymizer.controller

import ai.staircase.anonymizer.model.TextWrapper
import ai.staircase.anonymizer.service.AnonymizationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AnonymizationController(val anonymizationService: AnonymizationService) {

    @PostMapping("/anonymize")
    fun anonymize(@RequestBody textWrapper: TextWrapper): String =
        anonymizationService.anonymize(textWrapper.text)

}