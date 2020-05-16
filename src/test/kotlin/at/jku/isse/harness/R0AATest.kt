package at.jku.isse.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R0AATest : StringSpec({
    "should run through"{
        R0AA.run()
    }

    "equal results"{
        val requests = R0AA.loadData()

        val result = R0AA.allDevs.map { dev ->
            val clazz = Class.forName("${R0AA.packagePrefix}.$dev")
            val runMethod = clazz.getDeclaredMethod(R0AA.runMethod, requests[0].pattern::class.java, requests[0].num::class.java)


            val results = requests.map {
                runMethod.invoke(null, it.pattern, it.num)
            }

            Pair(dev, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe  result.first().second
        }
    }
})