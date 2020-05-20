package at.jku.isse.clones.harness

import mu.KotlinLogging
import java.lang.reflect.Method

object R1CB {
    private val logger = KotlinLogging.logger {}

    data class Request(val ac: Int, val aj: Int, val c: IntArray, val d: IntArray, val j: IntArray, val k: IntArray)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1CB0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1CB"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java, IntArray::class.java, IntArray::class.java, IntArray::class.java, IntArray::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            val requests = mutableListOf<Request>()
            var idx = 0
            while (idx < inputs.size) {
                val (ac, aj) = inputs[idx++].split(" ").let { Pair(it[0].toInt(), it[1].toInt()) }
                val c = mutableListOf<Int>()
                val d = mutableListOf<Int>()
                val j = mutableListOf<Int>()
                val k = mutableListOf<Int>()
                repeat(ac) { _ ->
                    inputs[idx++].split(" ").let {
                        c.add(it[0].toInt())
                        d.add(it[1].toInt())
                    }
                }
                repeat(aj) { _ ->
                    inputs[idx++].split(" ").let {
                        j.add(it[0].toInt())
                        k.add(it[1].toInt())
                    }
                }
                requests.add(Request(ac, aj, c.toIntArray(), d.toIntArray(), j.toIntArray(), k.toIntArray()))
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
                target.invoke(null, it.ac, it.aj, it.c, it.d, it.j, it.k)
            }
            logger.debug { "Finished $target" }
        }
    }
}