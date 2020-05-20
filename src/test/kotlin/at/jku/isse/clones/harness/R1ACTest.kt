package at.jku.isse.clones.harness

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class R1ACTest : StringSpec({
    "should run through".config(enabled=false){
        R1AC.run()
    }

    "equal results".config(enabled = false){
        val result = R1AC.targets(R1AC.allDevs).map { target ->
            val results = R1AC.requests.map {
                target.invoke(null, it.hd, it.ad, it.hk, it.ak, it.b, it.d)
            }

            Pair(target, results)
        }

        result.forAll {
            it.second shouldHaveSize 100
            it.second shouldBe result[0].second
        }
    }
})