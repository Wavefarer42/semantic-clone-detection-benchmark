package at.jku.isse.harness

object R0AB {
    data class Request(val num: Long)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R0AB0.txt"
    const val packagePrefix = "google.jam.r0AB"
    const val runMethod = "run"

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