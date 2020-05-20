package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.comparables.shouldBeLessThan

class R1BATest : StringSpec({
    "should run through"{
        R1BA.run()
    }

    "equal results".config() {
        val result = R1BA.targets(R1BA.allDevs).map { target ->
            val results = R1BA.requests.map {
                target.invoke(null, it.destination, it.positionCount, it.initialPosition, it.speed)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize R1BA.requests.size

            (it.second zip result[0].second)
                    .forEach {
                        kotlin.math.abs(it.first as Float - it.second as Float) shouldBeLessThan 0.01f
                    }
        }
    }
})