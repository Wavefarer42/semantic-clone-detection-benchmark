package at.jku.isse.harness

object Math {
    data class Request(val num: Int)

    val allDevs = (0..9).map { "Dev$it" }
    const val size = 1000
    const val packagePrefix = "at.jku.isse.math"
    const val runMethod = "run"

    fun loadData(): List<Request> {
        val sampler = Generator.uniform(0, 10)
        return (0 until size).map {
            Request(sampler())
        }
    }

    fun run(devs: List<String> = this.allDevs) {
        val requests = loadData()

        devs.forEach { dev ->
            val clazz = Class.forName("$packagePrefix.$dev")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].num::class.java)

            requests.forEach {
                runMethod.invoke(null, it.num)
            }
        }
    }
}