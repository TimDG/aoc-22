package aoc

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun `part 1`() {
        assertEquals(7, findStartMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(5, findStartMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"))
        assertEquals(6, findStartMarker("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(10, findStartMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(11, findStartMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))

    }

    @Test
    fun `part 2`() {
        assertEquals(19, findStartMessageMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
        assertEquals(23, findStartMessageMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"))
        assertEquals(23, findStartMessageMarker("nppdvjthqldpwncqszvftbrmjlhg"))
        assertEquals(29, findStartMessageMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
        assertEquals(26, findStartMessageMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }
}