package at.jku.isse.harness

object R0AB {
    data class Request(val num: Long)

    private const val resourceFile = "R0AB0.txt"
    private const val packagePrefix = "google.jam.r0AB"
    private const val runMethod = "run"

    fun loadData(): List<Request> {
        return Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            stream.reader()
                    .readLines()
                    .drop(1)
                    .map {
                        Request(it.toLong())
                    }
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