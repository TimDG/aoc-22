package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day2Test {
    private val input = """
        A Y
        B X
        C Z
    """.trimIndent()

    @Test
    fun `test part 1`() {
        val totalScore = input.lines().sumOf { simple(it) }
        assertEquals(15, totalScore)
    }

    @Test
    fun `test part 2`() {
        val optimalScore = input.lines().sumOf { optimal(it) }
        assertEquals(12, optimalScore)
    }
}