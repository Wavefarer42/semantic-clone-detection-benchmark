package at.jku.isse.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R0ACTest : StringSpec({
    "should run through"{
        R0AC.run()
    }

    "equal results"{
        val requests = R0AC.loadData()

        val result = R0AC.allDevs.map { dev ->
            val clazz = Class.forName("${R0AC.packagePrefix}.$dev")
            val runMethod = clazz.getDeclaredMethod(R0AC.runMethod, requests[0].l::class.java, requests[0].r::class.java)

            val results = requests.map {
                runMethod.invoke(null, it.l, it.r)
            }

            Pair(dev, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})