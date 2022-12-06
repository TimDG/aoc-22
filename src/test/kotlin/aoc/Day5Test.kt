package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {
    private val input = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
    """.trimIndent()

    @Test
    fun `part 1`() {
        val day5 = Day5(input)
        day5.executeInstructions()
        assertEquals("CMZ", day5.board.topCrates())
    }

    @Test
    fun `part 2`() {
        val day = Day5(input)
        day.executeInstructions9001()
        assertEquals("MCD", day.board.topCrates())
    }
}