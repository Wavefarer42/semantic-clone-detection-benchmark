package at.jku.isse.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R1AATest : StringSpec({
    "should run through"{
        R1AA.run()
    }

    "equal results"{
        val requests = R1AA.loadData()

        val result = R1AA.allDevs.map { dev ->
            val clazz = Class.forName("${R1AA.packagePrefix}.$dev")
            val runMethod = clazz.getDeclaredMethod(R1AA.runMethod, requests[0].r::class.java, requests[0].c::class.java, requests[0].pattern::class.java)

            val results = requests.map {
                runMethod.invoke(null, it.r, it.c, it.pattern)
            }

            Pair(dev, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})