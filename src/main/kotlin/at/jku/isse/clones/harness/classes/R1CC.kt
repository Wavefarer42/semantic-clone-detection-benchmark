package at.jku.isse.clones.harness.classes

import mu.KotlinLogging
import java.lang.reflect.Method

object R1CC {
    private val logger = KotlinLogging.logger {}

    data class Request(val n: Int, val k: Int, val u: Double, val ps: DoubleArray)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1CC0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1CC"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java, Double::class.java, DoubleArray::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            val requests = mutableListOf<Request>()
            var idx = 0
            while (idx < inputs.size) {
                val (n, k) = inputs[idx++].split(" ").let { Pair(it[0].toInt(), it[1].toInt()) }
                val u = inputs[idx++].toDouble()
                val ps = inputs[idx++].split(" ").map { it.toDouble() }.toDoubleArray()
                requests.add(Request(n, k, u, ps))
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
                target.invoke(null, it.n, it.k, it.u, it.ps)
            }
            logger.debug { "Finished $target" }
        }
    }
}