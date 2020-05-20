package at.jku.isse.clones.harness

import mu.KotlinLogging
import java.lang.reflect.Method

object R1CA {
    private val logger = KotlinLogging.logger {}

    data class Request(val n: Int, val k: Int, val r: IntArray, val h: IntArray)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1CA0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1CA"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java, IntArray::class.java, IntArray::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)


            val requests = mutableListOf<Request>()
            var idx = 0
            while (idx < inputs.size) {
                val (n, k) = inputs[idx++].split(" ").let { Pair(it[0].toInt(), it[1].toInt()) }
                val r = mutableListOf<Int>()
                val h = mutableListOf<Int>()
                repeat(n) { _ ->
                    inputs[idx++].split(" ").let {
                        r.add(it[0].toInt())
                        h.add(it[1].toInt())
                    }
                }

                requests.add(Request(n, k, r.toIntArray(), h.toIntArray()))
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
                target.invoke(null, it.n, it.k, it.r, it.h)
            }
            logger.debug { "Finished $target" }
        }
    }
}