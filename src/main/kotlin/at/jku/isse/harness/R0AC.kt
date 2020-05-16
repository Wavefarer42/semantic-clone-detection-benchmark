package at.jku.isse.harness

import java.lang.reflect.Method

object R0AC {
    data class Request(val l: Int, val r: Int)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R0AC0.txt"
    const val packagePrefix = "google.jam.r0AC"
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
            val clazz = Class.forName("${packagePrefix}.$it")
            clazz.getDeclaredMethod(runMethod, *targetMethodTypes)
        }
    }

    fun run(devs: List<String> = this.allDevs) {
        targets(devs).forEach { target ->
            requests.forEach {
                target.invoke(null, it.l, it.r)
            }
        }
    }
}