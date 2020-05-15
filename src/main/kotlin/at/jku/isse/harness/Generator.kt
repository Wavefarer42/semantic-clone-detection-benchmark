package at.jku.isse.harness

import java.util.*
import java.lang.Math as jMath

object Generator {
    val rng = Random(42)

    fun uniform(minimum: Int, maximum: Int): () -> Int = { rng.nextInt(maximum - minimum) + minimum }

    fun gaussian(location: Float, scale: Float, onlyPositive: Boolean = false): () -> Int = {
        val sample: Int = (rng.nextGaussian() * scale + location).toInt()
        if (onlyPositive) jMath.abs(sample) else sample
    }
}