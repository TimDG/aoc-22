package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7Test {
    private val input = """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """.trimIndent()

    @Test
    fun `part 1`() {
        val day7 = Day7(input)
        assertEquals(95437L, day7.sizes())
    }

    @Test
    fun `part 2`() {
        val day7 = Day7(input)
        assertEquals(24933642L, day7.findCleanupDir())
    }
}