package at.jku.isse.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R0ABTest : StringSpec({
    "should run through"{
        R0AB.run()
    }

    "equal results"{
        val requests = R0AB.loadData()

        val result = R0AB.allDevs.map { dev ->
            val clazz = Class.forName("${R0AB.packagePrefix}.$dev")
            val runMethod = clazz.getDeclaredMethod(R0AB.runMethod, requests[0].num::class.java)

            val results = requests.map {
                runMethod.invoke(null, it.num)
            }

            Pair(dev, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})