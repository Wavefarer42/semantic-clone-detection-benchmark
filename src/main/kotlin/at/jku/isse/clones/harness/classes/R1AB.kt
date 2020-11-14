package at.jku.isse.clones.harness.classes

import mu.KotlinLogging
import java.lang.reflect.Method

object R1AB {
    private val logger = KotlinLogging.logger {}

    data class Request(val n: Int, val p: Int, val ingredientQuantities: IntArray, val packages: Array<IntArray>)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1AB0.txt"
    const val packagePrefix = "at.jku.isse.clones.r1AB"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Int::class.java, Int::class.java, IntArray::class.java, Array<IntArray>::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            val requests = mutableListOf<Request>()
            var idx = 0
            while (idx < inputs.size) {
                val testCase = inputs[idx].split(" ")
                val n = testCase[0].toInt()
                val p = testCase[1].toInt()
                idx++



                val ingredientQuantities = inputs[idx++].split(" ").map { it.toInt() }.toIntArray()

                val packages = Array(n) { _ ->
                    inputs[idx++].split(" ").map { it.toInt() }.toIntArray()
                }

                requests.add(Request(n, p, ingredientQuantities, packages))
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
                target.invoke(null, it.n, it.p, it.ingredientQuantities, it.packages)
            }
            logger.debug { "Finished $target" }
        }
    }
}