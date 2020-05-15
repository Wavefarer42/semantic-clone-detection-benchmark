package at.jku.isse.harness

import at.jku.isse.clones.*
import google.jam.r0AD.Model


const val SEED = 14L

fun main() {
    Math.rng.setSeed(SEED)
    factorialBenchmark()
    fibonacciBenchmark()
    bubbleSortBenchmark()
    mergeSortBenchmark()
    R0AA0()
    R0AB0()
    R0AC0()
    R0AD0() // to memory intensive
    R1AA0()
    R1AB.run()
}

private fun isSorted(list: List<Int>): Boolean {
    for (i in 0 until (list.size - 1)) {
        if (list[i] > list[i + 1]) {
            return false
        }
    }
    return true
}

private fun factorialBenchmark() {
    val sampler = Math.uniform(0, 10)
    repeat(1000) {
        val data = sampler()
        val result = Factorial.iterative(data)

        println("Factorial (iterative): n = $data, result = $result")
    }

    repeat(100) {
        val data = sampler()
        val result = Factorial.recursive(data)

        println("Factorial (recursive): n = $data, result = $result")
    }
}

private fun fibonacciBenchmark() {
    val sampler = Math.uniform(0, 10)
    repeat(1000) {
        val data = sampler()
        val result = Fibonacci.iterative(data)

        println("Fibonacci (iterative): n = $data, result = $result")
    }

    repeat(100) {
        val data = sampler()
        val result = Fibonacci.recursive(data)

        println("Fibonacci (recursive): n = $data, result = $result")
    }
}

private fun bubbleSortBenchmark() {
    val sampler = Math.uniform(0, 20)

    repeat(1000) {
        val data = IntArray(20) { sampler() }.asList()
        val result = BubbleSort.iterative(data)

        println("BubbleSort (iterative): trial = $it, inputIsSorted = ${isSorted(data)}, outputIsSorted = ${isSorted(result)}")
    }

    repeat(500) {
        val data = IntArray(20) { sampler() }.asList()
        val result = BubbleSort.recursive(data)

        println("BubbleSort (recursive): trial = $it, inputIsSorted = ${isSorted(data)}, outputIsSorted = ${isSorted(result)}")
    }
}

private fun mergeSortBenchmark() {
    val sampler = Math.uniform(0, 20)
    repeat(1000) {
        val data = IntArray(20) { sampler() }.asList()
        val result = MergeSort.iterative(data)

        println("MergeSort (iterative): trial = $it, inputIsSorted = ${isSorted(data)}, outputIsSorted = ${isSorted(result)}")
    }
    repeat(500) {
        val data = IntArray(20) { sampler() }.asList()
        val result = MergeSort.recursive(data)

        println("MergeSort (recursive): trial = $it, inputIsSorted = ${isSorted(data)}, outputIsSorted = ${isSorted(result)}")
    }
}

private fun R0AA0() {
    val numOfDevs = 5

    Thread.currentThread().contextClassLoader.getResourceAsStream("R0AA0.txt")!!.use { stream ->
        val requests = stream.reader()
                .readLines()
                .drop(1)
                .map {
                    val parts = it.split(" ")
                    Pair(parts[0], parts[1].toInt())
                }

        for (i in 0 until numOfDevs) {
            val clazz = Class.forName("google.jam.r0AA.Dev$i")
            val runMethod = clazz.getDeclaredMethod("run", String::class.java, Int::class.java)
            requests.forEach {
                runMethod.invoke(null, it.first, it.second)
            }
        }
    }
}

private fun R0AB0() {
    val numOfDevs = 5

    Thread.currentThread().contextClassLoader.getResourceAsStream("R0AB0.txt")!!.use { stream ->
        val requests = stream.reader()
                .readLines()
                .drop(1)
                .map {
                    it.toLong()
                }

        for (i in 0 until numOfDevs) {
            val clazz = Class.forName("google.jam.r0AB.Dev$i")
            val runMethod = clazz.getDeclaredMethod("run", Long::class.java)
            requests.forEach {
                runMethod.invoke(null, it)
            }
        }
    }
}

private fun R0AC0() {
    val numOfDevs = 5

    Thread.currentThread().contextClassLoader.getResourceAsStream("R0AC0.txt")!!.use { stream ->
        val requests = stream.reader()
                .readLines()
                .drop(1)
                .map {
                    val values = it.split(" ")
                    Pair(Integer.parseInt(values[0]), Integer.parseInt(values[1]))
                }

        for (i in 0 until numOfDevs) {
            val clazz = Class.forName("google.jam.r0AC.Dev$i")
            val runMethod = clazz.getDeclaredMethod("run", Int::class.java, Int::class.java)
            requests.forEach {
                runMethod.invoke(null, it.first, it.second)
            }
        }
    }
}

private fun R0AD0() {
    val numOfDevs = 5

    Thread.currentThread().contextClassLoader.getResourceAsStream("R0AD0.txt")!!.use { stream ->
        val inputs = stream.reader()
                .readLines()
                .drop(1)

        val requests = mutableListOf<Triple<Int, Int, List<Model>>>()
        var idx = 0
        while (idx < inputs.size) {
            val testCase = inputs[idx].split(" ")
            val n = testCase[0].toInt()
            val m = testCase[1].toInt()

            val models = mutableListOf<Model>()
            for (midx in 0 until m) {
                idx++
                val modelStr = inputs[idx].split(" ")
                assert(modelStr.size == 3)
                models.add(Model(modelStr[0].first(), modelStr[1].toInt(), modelStr[2].toInt()))
            }
            requests.add(Triple(n, m, models))

            idx++
        }

        for (i in 0 until numOfDevs) {
            val clazz = Class.forName("google.jam.r0AD.Dev$i")
            val runMethod = clazz.getDeclaredMethod("run", Int::class.java, Int::class.java, List::class.java)
            requests.forEach {
                runMethod.invoke(null, it.first, it.second, it.third)
            }
        }
    }
}

private fun R1AA0() {
    val numOfDevs = 5

    Thread.currentThread().contextClassLoader.getResourceAsStream("R1AA0.txt")!!.use { stream ->
        val inputs = stream.reader()
                .readLines()
                .drop(1)

        val requests = mutableListOf<Triple<Int, Int, List<String>>>()
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

            requests.add(Triple(r, c, devs))
            idx++
        }

        for (i in 0 until numOfDevs) {
            val clazz = Class.forName("google.jam.r1AA.Dev$i")
            val runMethod = clazz.getDeclaredMethod("run", Int::class.java, Int::class.java, List::class.java)
            requests.forEach {
                runMethod.invoke(null, it.first, it.second, it.third)
            }
        }
    }
}
