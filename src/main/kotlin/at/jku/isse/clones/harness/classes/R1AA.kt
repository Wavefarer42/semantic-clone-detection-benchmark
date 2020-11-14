package at.jku.isse.clones.harness.classes

import mu.KotlinLogging
import java.lang.reflect.Method

object R1AA {
    private val logger = KotlinLogging.logger {}

    data class Request(val r: Int, val c: Int, val pattern: List<String>)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1AA0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1AA"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java, List::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            val requests = mutableListOf<Request>()
            var idx = 0
            while (idx < inputs.size) {
                val testCase = inputs[idx].split(" ")
                val r = testCase[0].toInt()
                val c = testCase[1].toInt()

                val devs = mutableListOf<String>()
                for (midx in 0 until r) {
                    idx++
                    devs.add(inputs[idx])
                }

                requests.add(Request(r, c, devs))
                idx++
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
                target.invoke(null, it.r, it.c, it.pattern)
            }
            logger.debug { "Finished $target" }
        }
    }
}