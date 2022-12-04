package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day3Test  {
    private val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent()

    @Test
    internal fun `test part 1`() {
        val day3 = Day3(input)
        assertEquals(157, day3.prioritySum())
    }

    @Test
    internal fun `test part 2`() {
        val day3 = Day3(input)
        assertEquals(70,  day3.groupedPriorities())
    }
}