package at.jku.isse.harness

object R0AA {
    data class Request(val pattern: String, val num: Int)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R0AA0.txt"
    const val packagePrefix = "google.jam.r0AA"
    const val runMethod = "run"

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

    fun run(devs: List<String> = this.allDevs) {
        val requests = loadData()

        devs.forEach { dev ->
            val clazz = Class.forName("$packagePrefix.$dev")
            val runMethod = clazz.getDeclaredMethod(runMethod, requests[0].pattern::class.java, requests[0].num::class.java)

            requests.forEach {
                runMethod.invoke(null, it.pattern, it.num)
            }
        }
    }
}