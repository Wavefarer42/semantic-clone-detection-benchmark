package at.jku.isse.clones.harness

import mu.KotlinLogging
import java.lang.reflect.Method

object R0AB {
    private val logger = KotlinLogging.logger {}

    data class Request(val num: Long)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R0AB0.txt"
    const val packagePrefix = "at.jku.isse.clones.r0AB"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Long::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            stream.reader()
                    .readLines()
                    .drop(1)
                    .map {
                        Request(it.toLong())
                    }
        }
    }

    fun targets(devs: List<String>): List<Method> {
        return devs.map {
            val clazz = Class.forName("$packagePrefix.$it")
            clazz.getDeclaredMethod(runMethod, *targetMethodTypes)
        }
    }

    fun run(devs: List<String> = allDevs) {
        targets(devs).forEach { target ->
            logger.debug { "Running $target" }
            requests.forEach {
                target.invoke(null, it.num)
            }
            logger.debug { "Finished $target" }
        }
    }
}