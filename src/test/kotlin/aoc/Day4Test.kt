package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day4Test {

    private val input = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent()

    @Test
    fun `create assignment`() {
        val str = "2-6,6-8"

        val actual = Assignment(str)

        val expected = Assignment(IntRange(2, 6), IntRange(6, 8))

        assertEquals(expected, actual)
    }

    @Test
    fun `test overlap`() {
        assertTrue(Assignment(IntRange(1, 5), IntRange(2, 4)).fullyContained())
        assertTrue(Assignment(IntRange(1, 5), IntRange(1, 2)).fullyContained())
        assertTrue(Assignment(IntRange(1, 5), IntRange(2, 5)).fullyContained())
        assertTrue(Assignment(IntRange(1, 5), IntRange(5, 5)).fullyContained())
        assertTrue(Assignment(IntRange(1, 2), IntRange(1, 5)).fullyContained())
        assertTrue(Assignment(IntRange(2, 5), IntRange(1, 5)).fullyContained())
        assertTrue(Assignment(IntRange(2, 4), IntRange(1, 5)).fullyContained())
        assertTrue(Assignment(IntRange(5, 5), IntRange(1, 5)).fullyContained())

        assertFalse(Assignment(IntRange(1, 5), IntRange(6, 7)).fullyContained())
        assertFalse(Assignment(IntRange(6, 7), IntRange(1, 5)).fullyContained())

        assertFalse(Assignment(IntRange(1, 5), IntRange(5, 8)).fullyContained())
        assertFalse(Assignment(IntRange(5, 8), IntRange(1, 5)).fullyContained())
    }

    @Test
    internal fun `part 1`() {
        val day4 = Day4(input)

        assertEquals(2, day4.countFullOverlaps())
    }

    @Test
    fun `part 2`() {
        assertEquals(4, Day4(input).countOverlaps())
    }
}