package ai.staircase.anonymizer.repository

import java.util.concurrent.ConcurrentHashMap

/**
 * In-memory cache for java-faker.
 * Should go to redis or some other shared cache storage
 */
abstract class FakeDataRepository {

    private val cache = ConcurrentHashMap<String, String>()

    fun getFake(origin: String): String =
        cache.computeIfAbsent(origin) {
            buildFake()
        }


    protected abstract fun buildFake(): String

}