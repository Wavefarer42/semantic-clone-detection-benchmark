package at.jku.isse.clones.harness

import at.jku.isse.clones.harness.classes.R0AA
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R0AATest : StringSpec({
    "should run through"{
        R0AA.run()
    }

    "equal results"{
        val result = R0AA.targets(R0AA.allDevs).map { target ->
            val results = R0AA.requests.map {
                target.invoke(null, it.pattern, it.num)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result.first().second
        }
    }
})