package at.jku.isse.clones.harness

import at.jku.isse.clones.harness.classes.R2AA
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R2AATest : StringSpec({
    "should run through"{
        R2AA.run()
    }

    "equal results"{
        val result = R2AA.targets(R2AA.allDevs).map { target ->
            val results = R2AA.requests.map {
                target.invoke(null, it.n, it.p, it.g)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize R2AA.requests.size
            it.second shouldBe result[0].second
        }
    }
})