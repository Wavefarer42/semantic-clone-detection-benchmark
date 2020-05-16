package at.jku.isse.harness

object R1AB {
    data class Request(val n: Int, val p: Int, val ingredientQuantities: IntArray, val packages: Array<IntArray>)

    val allDevs = (0..9).map { "Dev$it" }
    const val resourceFile = "R1AB0.txt"
    const val packagePrefix = "google.jam.r1AB"
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
                val n = testCase[0].toInt()
                val p = testCase[1].toInt()
                idx++

                val ingredientQuantities = inputs[idx++].split(" ").map { it.toInt() }.toIntArray()

                val packages = Array(n) { i ->
                    inputs[idx++].split(" ").map { it.toInt() }.toIntArray()
                }

                requests.add(Request(n, p, ingredientQuantities, packages))
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
                    requests[0].n::class.java, requests[0].p::class.java, requests[0].ingredientQuantities::class.java, requests[0].packages::class.java
            )

            requests.forEach {
                runMethod.invoke(null, it.n, it.p, it.ingredientQuantities, it.packages)
            }
        }
    }
}