package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R0ABTest : StringSpec({
    "should run through"{
        R0AB.run()
    }

    "equal results"{
        val result = R0AB.targets(R0AB.allDevs).map { target ->
            val results = R0AB.requests.map {
                target.invoke(null, it.num)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})