package at.jku.isse.clones.harness

import at.jku.isse.clones.harness.classes.R1CC
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.doubles.shouldBeLessThanOrEqual
import kotlin.math.absoluteValue

class R1CCTest : StringSpec({
    "should run through"{
        R1CC.run()
    }

    "equal results"{
        val result = R1CC.targets(R1CC.allDevs).map { target ->
            val results = R1CC.requests.map {
                target.invoke(null, it.n, it.k, it.u, it.ps)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize R1CC.requests.size
            (it.second zip result[0].second)
                    .map { it.first as Double to it.second as Double }
                    .forAll {
                        (it.first - it.second).absoluteValue shouldBeLessThanOrEqual 1e-2
                    }
        }
    }
})