package at.jku.isse.clones.mutator.mutations

import spoon.processing.AbstractProcessor
import spoon.reflect.code.CtAssignment
import spoon.reflect.code.CtRHSReceiver

class IntegerMutation : AbstractProcessor<CtAssignment<Int, Int>>() {
    override fun process(element: CtAssignment<Int, Int>) {
        val mutatedAssignment = factory.createCodeSnippetExpression<Int>("${element.assignment} + 1 - 1")
        element.setAssignment<CtRHSReceiver<Int>>(mutatedAssignment)
    }
}