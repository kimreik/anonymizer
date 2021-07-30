package ai.staircase.anonymizer.service.replacer

interface Replacer {

    fun replace(text: String): String

}