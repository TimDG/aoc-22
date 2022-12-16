package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {

    private val input = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent()

    @Test
    fun `part 1`() {
        val day9 = Day9(input)
        assertEquals(13, day9.positions.size)
    }

    @Test
    fun `part 2`() {
        val day9 = Day9("""
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent(), 10)
        assertEquals(36, day9.positions.size)
    }
}