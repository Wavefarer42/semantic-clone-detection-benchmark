package at.jku.isse.harness

object R1AA {
    data class Request(val r: Int, val c: Int, val pattern: List<String>)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1AA0.txt"
    const val packagePrefix = "google.jam.r1AA"
    const val runMethod = "run"

    fun loadData(): List<Request> {
        return Thread.currentThread().contextClassLoader.getResourceAsStream(resourceFile)!!.use { stream ->
            val inputs = stream.reader()
                    .readLines()
                    .drop(1)

            val requests = mutableListOf<Request>()
            var idx = 0
            while (idx < inputs.size) {
                val testCase = inputs[idx].split(" ")
                val r = testCase[0].toInt()
                val c = testCase[1].toInt()

                val devs = mutableListOf<String>()
                for (midx in 0 until r) {
                    idx++
                    devs.add(inputs[idx])
                }

                requests.add(Request(r, c, devs))
                idx++
            }
            requests
        }
    }

    fun run(devs: List<String> = this.allDevs) {
        val requests = loadData()

        devs.forEach { dev ->
            val clazz = Class.forName("$packagePrefix.$dev")
            val runMethod = clazz.getDeclaredMethod(
                    runMethod,
                    requests[0].r::class.java, requests[0].c::class.java, requests[0].pattern::class.java
            )

            requests.forEach {
                runMethod.invoke(null, it.r, it.c, it.pattern)
            }
        }
    }
}