package at.jku.isse.clones

import at.jku.isse.clones.harness.HarnessService
import at.jku.isse.clones.mutator.MutationService
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class Args(parser: ArgParser) {
    val command by parser.positional("command (harness|mutate)")
    val inputDirectory by parser.storing("-i", help = "Directory where the sources are located").default("src")
    val outputDirectory by parser.storing("-o", help = "Directory where the sources should be written").default("output")
}

fun main(args: Array<String>) {
    ArgParser(args).parseInto(::Args).run {
        when (command) {
            "harness" -> HarnessService().runAll()
            "mutate" -> MutationService().mutateAll(inputDirectory, outputDirectory)
            else -> throw IllegalArgumentException("Unknown command")
        }
    }
}
