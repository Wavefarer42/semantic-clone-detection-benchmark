package at.jku.isse.harness

object Sort {
    data class Request(val list: List<Int>)

    val allDevs = (0..3).map { "Dev$it" }
    const val size = 1000
    const val packagePrefix = "at.jku.isse.sort"
    const val runMethod = "run"

    fun loadData(): List<Request> {
        val sampler = Generator.uniform(0, 20)
        return (0 until size).map {
            Request(IntArray(20) { sampler() }.asList())
        }
    }

    fun run(devs: List<String> = this.allDevs) {
        val requests = loadData()

        devs.forEach { dev ->
            val clazz = Class.forName("$packagePrefix.$dev")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].list::class.java)

            requests.forEach {
                runMethod.invoke(null, it.list)
            }
        }
    }
}