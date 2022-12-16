package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {
    private val input = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent()

    @Test
    fun `part 1`() {
        val day8 = Day8(input)
        assertEquals(21, day8.getVisibleTrees())
    }

    @Test
    fun `part 2`() {
        val day8 = Day8(input)

        day8.getVisibleTrees()
        assertEquals(8,  day8.scenicScore())
    }
}