package at.jku.isse.clones.harness.classes

import mu.KotlinLogging
import java.lang.reflect.Method

object R2AA {
    private val logger = KotlinLogging.logger {}

    data class Request(val n: Int, val p: Int, val g: IntArray)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R2AA0.txt"
    const val packagePrefix = "at.jku.isse.clones.r2AA"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java, IntArray::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            val requests = mutableListOf<Request>()
            var idx = 0
            while (idx < inputs.size) {
                val (n, p) = inputs[idx++].split(" ").let { Pair(it[0].toInt(), it[1].toInt()) }
                val g = inputs[idx++].split(" ").map { it.toInt() }.toIntArray()
                requests.add(Request(n, p, g))
            }
            requests
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
                target.invoke(null, it.n, it.p, it.g)
            }
            logger.debug { "Finished $target" }
        }
    }
}