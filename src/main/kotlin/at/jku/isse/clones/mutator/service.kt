package at.jku.isse.clones.mutator

import at.jku.isse.clones.mutator.mutations.IntegerMutation
import spoon.support.JavaOutputProcessor

class MutationService {
    companion object {
        val allMutations = setOf(
                IntegerMutation()
        )
    }

    fun mutateAll(sourceDirectory: String, outputDirectory: String) {
        spoon.FluentLauncher()
                .inputResource(sourceDirectory)
                .processor(allMutations)
                .processor(JavaOutputProcessor())
                .outputDirectory(outputDirectory)
                .buildModel()
    }
}