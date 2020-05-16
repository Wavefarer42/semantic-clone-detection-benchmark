package at.jku.isse.harness

import java.lang.reflect.Method

object Search {
    data class Request(val list: List<Int>)

    val allDevs = (0..1).map { "Dev$it" }
    const val size = 1000
    const val packagePrefix = "at.jku.isse.search"
    const val runMethod = "run"
    val targetMethodTypes = arrayOf(List::class.java)

    val requests: List<Request> by lazy {
        val sampler = Generator.uniform(0, 20)
        (0 until size).map {
            Request(IntArray(20) { sampler() }.asList())
        }
    }

    fun targets(devs: List<String>): List<Method> {
        return devs.map {
            val clazz = Class.forName("${packagePrefix}.$it")
            clazz.getDeclaredMethod(runMethod, *targetMethodTypes)
        }
    }

    fun run(devs: List<String> = this.allDevs) {
        targets(devs).forEach { targets ->
            requests.forEach {
                targets.invoke(null, it.list)
            }
        }
    }
}