package at.jku.isse.harness

object Math {
    data class Request(val num: Int)

    private const val size = 1000
    private const val packagePrefix = "at.jku.isse.math"
    private const val runMethod = "run"

    fun loadData(): List<Request> {
        val sampler = Generator.uniform(0, 10)
        return (0 until size).map {
            Request(sampler())
        }
    }

    fun run(devs: IntRange = 0..10) {
        val requests = loadData()

        for (i in devs) {
            val clazz = Class.forName("$packagePrefix.Dev$i")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].num::class.java)

            requests.forEach {
                runMethod.invoke(null, it.num)
            }
        }
    }
}