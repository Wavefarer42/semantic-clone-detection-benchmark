package at.jku.isse.harness

object R0AA {
    data class Request(val pattern: String, val num: Int)

    private const val resourceFile = "R0AA0.txt"
    private const val packagePrefix = "google.jam.r0AA"
    private const val runMethod = "run"

    fun loadData(): List<Request> {
        return Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            stream.reader()
                    .readLines()
                    .drop(1)
                    .map {
                        val parts = it.split(" ")
                        Request(parts[0], parts[1].toInt())
                    }
        }
    }

    fun run(devs: IntRange = 0..10) {
        val requests = loadData()

        for (i in devs) {
            val clazz = Class.forName("$packagePrefix.Dev$i")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].pattern::class.java, requests[0].num::class.java)

            requests.forEach {
                runMethod.invoke(null, it.pattern, it.num)
            }
        }
    }
}