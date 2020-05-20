package at.jku.isse.clones.harness

import mu.KotlinLogging
import java.lang.reflect.Method

object R0AC {
    private val logger = KotlinLogging.logger{}
    data class Request(val l: Int, val r: Int)

    val allDevs = ((0..5) + (7..9)).map { "Dev$it" }
    const val resourceFile = "R0AC0.txt"
    const val packagePrefix = "at.jku.isse.clones.r0AC"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            stream.reader()
                    .readLines()
                    .drop(1)
                    .map {
                        val values = it.split(" ")
                        Request(values[0].toInt(), values[1].toInt())
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
                    target.invoke(null, it.l, it.r)
                }
            logger.debug { "Finished $target" }
        }
    }
}