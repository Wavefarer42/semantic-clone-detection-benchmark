package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R1BBTest : StringSpec({
    "should run through"{
        R1BB.run()
    }

    "input is deterministic"{
        val ref = R1BB.requests
        repeat(3){
            ref shouldBe R1BB.requests
        }
    }

    "equal results"{
        val result = R1BB.targets(R1BB.allDevs).map { target ->
            val results = R1BB.requests.map {
                target.invoke(null, it.n, it.colors)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize R1BB.requests.size
            it.second shouldBe result[0].second
        }
    }
})