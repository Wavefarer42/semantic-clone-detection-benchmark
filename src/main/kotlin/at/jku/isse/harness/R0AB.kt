package at.jku.isse.harness

import java.lang.reflect.Method

object R0AB {
    data class Request(val num: Long)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R0AB0.txt"
    const val packagePrefix = "google.jam.r0AB"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(Long::class.java)

    val requests: List<Request> by lazy {
        Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            stream.reader()
                    .readLines()
                    .drop(1)
                    .map {
                        Request(it.toLong())
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
                target.invoke(null, it.num)
            }
        }
    }
}