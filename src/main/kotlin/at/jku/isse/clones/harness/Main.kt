package at.jku.isse.clones.harness

import mu.KotlinLogging

val logger = KotlinLogging.logger { }

fun main() {

    logger.debug { "Starting to executable the clones classes" }
    Math.run()
    Sort.run()
//    Search.run()

    R0AA.run()
    R0AB.run()
    R0AC.run()

    R1AA.run()
    R1AB.run()

    R1BA.run()

    R1CA.run()
    R1CB.run()
    R1CC.run()

    R2AA.run()
    logger.debug { "Finished running clone classes" }
}
