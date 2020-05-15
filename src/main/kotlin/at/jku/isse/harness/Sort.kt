package at.jku.isse.harness

object Sort {
    data class Request(val list: List<Int>)

    private const val size = 1000
    private const val packagePrefix = "at.jku.isse.sort"
    private const val runMethod = "run"

    fun loadData(): List<Request> {
        val sampler = Generator.uniform(0, 20)
        return (0 until size).map {
            Request(IntArray(20) { sampler() }.asList())
        }
    }

    fun run(devs: IntRange = 0..10) {
        val requests = loadData()

        for (i in devs) {
            val clazz = Class.forName("$packagePrefix.Dev$i")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].list::class.java)

            requests.forEach {
                runMethod.invoke(null, it.list)
            }
        }
    }
}