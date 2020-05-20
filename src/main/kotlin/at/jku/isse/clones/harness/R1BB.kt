package at.jku.isse.clones.harness

import mu.KotlinLogging
import java.lang.reflect.Method

object R1BB {
    private val logger = KotlinLogging.logger {}

    data class Request(val n: Int, val colors: IntArray)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1BB0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1BB"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, IntArray::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            inputs.map { input ->
                val row = input.split(" ")
                        .map { it.toInt() }
                Request(row.first(), row.drop(1).toIntArray())
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
                target.invoke(null, it.n, it.colors)
            }
            logger.debug { "Finished $target" }
        }
    }
}