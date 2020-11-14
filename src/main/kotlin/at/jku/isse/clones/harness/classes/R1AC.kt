package at.jku.isse.clones.harness.classes

import mu.KotlinLogging
import java.lang.reflect.Method

object R1AC {
    private val logger = KotlinLogging.logger {}

    data class Request(val hd: Int, val ad: Int, val hk: Int, val ak: Int, val b: Int, val d: Int)

    val allDevs = (1..9).map { "Dev$it" }
    const val resourceFile = "R1AC0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1AC"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java, Int::class.java, Int::class.java, Int::class.java, Int::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            inputs.map { text ->
                val row = text.split(" ").map { it.toInt() }
                Request(row[0], row[1], row[2], row[3], row[4], row[5])
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
                target.invoke(null, it.hd, it.ad, it.hk, it.ak, it.b, it.d)
            }
            logger.debug { "Finished $target" }
        }
    }
}