package at.jku.isse.harness

import java.lang.reflect.Method

object R0AA {
    data class Request(val pattern: String, val num: Int)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R0AA0.txt"
    const val packagePrefix = "google.jam.r0AA"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(String::class.java, Int::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            stream.reader()
                    .readLines()
                    .drop(1)
                    .map {
                        val parts = it.split(" ")
                        Request(parts[0], parts[1].toInt())
                    }
        }
    }

    fun targets(devs: List<String>): List<Method> {
        return devs.map {
            val clazz = Class.forName("$packagePrefix.$it")
            clazz.getDeclaredMethod(runMethod, *targetMethodTypes)
        }
    }

    fun run(devs: List<String> = this.allDevs) {
        targets(devs).forEach { target ->
            requests.forEach {
                target.invoke(null, it.pattern, it.num)
            }
        }
    }
}