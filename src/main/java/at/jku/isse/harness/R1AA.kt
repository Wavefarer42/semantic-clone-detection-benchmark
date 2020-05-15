package at.jku.isse.harness

object R1AA {
    data class Request(val r: Int, val c: Int, val pattern: List<String>)

    private const val resourceFile = "R1AA0.txt"
    private const val packagePrefix = "google.jam.r1AA"
    private const val runMethod = "run"

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

    fun run(devs: IntRange = 0..10) {
        val requests = loadData()

        for (i in devs) {
            val clazz = Class.forName("$packagePrefix.Dev$i")
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