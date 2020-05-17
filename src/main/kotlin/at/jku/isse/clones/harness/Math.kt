package at.jku.isse.clones.harness

import mu.KotlinLogging
import java.lang.reflect.Method

object Math {
    private val logger = KotlinLogging.logger {}

    data class Request(val num: Int)

    val allDevs = (0..9).map { "Dev$it" }
    const val size = 1000
    const val packagePrefix = "at.jku.isse.clones.math"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java)

    val requests: List<Request> by lazy {
        val sampler = Generator.uniform(0, 10)
        (0 until size).map {
            Request(sampler())
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
        }
    }
}