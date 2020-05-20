package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R1ABTest : StringSpec({
    "should run through"{
        R1AB.run()
    }

    "equal results"{
        val result = R1AB.targets(R1AB.allDevs).map { target ->
            val results = R1AB.requests.map {
                target.invoke(null, it.n, it.p, it.ingredientQuantities, it.packages)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result[3].second
        }
    }
})