package at.jku.isse.clones.harness

import mu.KotlinLogging
import java.lang.reflect.Method

object R1BA {
    private val logger = KotlinLogging.logger {}

    data class Request(val destination: Int, val positionCount: Int, val initialPosition: IntArray, val speed: IntArray)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1BA0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1BA"
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
                val testCase = inputs[idx].split(" ")
                val d = testCase[0].toInt()
                val n = testCase[1].toInt()
                idx++

                val positions = mutableListOf<Int>()
                val speed = mutableListOf<Int>()
                repeat(n) {
                    val entry = inputs[idx].split(" ").map { it.toInt() }
                    positions.add(entry[0])
                    speed.add(entry[1])
                    idx++
                }

                requests.add(Request(d, n, positions.toIntArray(), speed.toIntArray()))
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
                target.invoke(null, it.destination, it.positionCount, it.initialPosition, it.speed)
            }
            logger.debug { "Finished $target" }
        }
    }
}