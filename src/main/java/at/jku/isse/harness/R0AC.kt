package at.jku.isse.harness

object R0AC {
    data class Request(val l: Int, val r: Int)

    private const val resourceFile = "R0AC0.txt"
    private const val packagePrefix = "google.jam.r0AC"
    private const val runMethod = "run"

    fun loadData(): List<Request> {
        return Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            stream.reader()
                    .readLines()
                    .drop(1)
                    .map {
                        val values = it.split(" ")
                        Request(values[0].toInt(), values[1].toInt())
                    }
        }
    }

    fun run(devs: IntRange = 0..10) {
        val requests = loadData()

        for (i in devs) {
            val clazz = Class.forName("$packagePrefix.Dev$i")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].l::class.java, requests[0].r::class.java)

            requests.forEach {
                runMethod.invoke(null, it.l, it.r)
            }
        }
    }
}