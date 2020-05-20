package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.doubles.shouldBeLessThanOrEqual
import kotlin.math.absoluteValue

class R1CATest : StringSpec({
    "should run through"{
        R1CA.run()
    }

    "equal results"{
        val result = R1CA.targets(R1CA.allDevs).map { target ->
            val results = R1CA.requests.map {
                target.invoke(null, it.n, it.k, it.r, it.h)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize R1CA.requests.size
            (it.second zip result[0].second)
                    .map { it.first as Double to it.second as Double }
                    .forAll {
                        (it.first - it.second).absoluteValue shouldBeLessThanOrEqual 1e-1
                    }
        }
    }
})