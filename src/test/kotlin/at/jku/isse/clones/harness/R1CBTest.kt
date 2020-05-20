package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R1CBTest : StringSpec({
    "should run through"{
        R1CB.run()
    }

    "equal results"{
        val result = R1CB.targets(R1CB.allDevs).map { target ->
            val results = R1CB.requests.map {
                target.invoke(null, it.ac, it.aj, it.c, it.d, it.j, it.k)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize R1CA.requests.size
            it.second shouldBe result[0].second
        }
    }
})