package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day1Puzzle1KtTest {

    private var data: String = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent()


    @Test
    fun `test part 1`() {
        val most = Day1Puzzle1(data).elfCarryingMost()

        assertEquals(24000, most)
    }

    @Test
    fun `test part 2`() {
        val topThree = Day1Puzzle1(data).topThree()

        assertEquals(45000, topThree)
    }
}