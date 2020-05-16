package at.jku.isse.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R1ABTest : StringSpec({
    "should run through"{
        R1AB.run()
    }

    "equal results"{
        val requests = R1AB.loadData()

        val result = R1AB.allDevs.map { dev ->
            val clazz = Class.forName("${R1AB.packagePrefix}.$dev")
            val runMethod = clazz.getDeclaredMethod(R1AB.runMethod, requests[0].n::class.java, requests[0].p::class.java, requests[0].ingredientQuantities::class.java, requests[0].packages::class.java)

            val results = requests.map {
                runMethod.invoke(null, it.n, it.p, it.ingredientQuantities, it.packages)
            }

            Pair(dev, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})