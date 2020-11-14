package at.jku.isse.clones.harness

import at.jku.isse.clones.harness.classes.R1AA
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R1AATest : StringSpec({
    "should run through"{
        R1AA.run()
    }

    "equal results"{
        val result = R1AA.targets(R1AA.allDevs).map { target ->
            val results = R1AA.requests.map {
                target.invoke(null, it.r, it.c, it.pattern)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})