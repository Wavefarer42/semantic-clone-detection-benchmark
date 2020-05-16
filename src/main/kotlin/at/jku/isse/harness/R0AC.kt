package at.jku.isse.harness

object R0AC {
    data class Request(val l: Int, val r: Int)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R0AC0.txt"
    const val packagePrefix = "google.jam.r0AC"
    const val runMethod = "run"

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

    fun run(devs: List<String> = this.allDevs) {
        val requests = loadData()

        devs.forEach { dev ->
            val clazz = Class.forName("$packagePrefix.$dev")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].l::class.java, requests[0].r::class.java)

            requests.forEach {
                runMethod.invoke(null, it.l, it.r)
            }
        }
    }
}