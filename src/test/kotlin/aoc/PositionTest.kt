package aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

class PositionTest {
    @Test
    fun `move up`() {
        val (x, y) = getLocations()
        val position = Position(x, y)

        assertEquals(Position(x, y + 1), position.moveUp())
    }

    @Test
    fun `move down`() {
        val (x, y) = getLocations()
        val position = Position(x, y)

        assertEquals(Position(x, y - 1), position.moveDown())
    }

    @Test
    fun `move left`() {
        val (x, y) = getLocations()
        val position = Position(x, y)

        assertEquals(Position(x - 1, y), position.moveLeft())
    }

    @Test
    fun `move right`() {
        val (x, y) = getLocations()
        val position = Position(x, y)

        assertEquals(Position(x + 1, y), position.moveRight())
    }

    @Test
    fun `move to same position`() {
        val (x, y) = getLocations()
        val target = Position(x, y)
        val current = Position(x, y)

        assertEquals(current, current.moveTo(target))
    }

    @Test
    fun `bordering squares means no movement`() {
        val (x, y) = getLocations()
        val target = Position(x, y)

        for (i in (-1..1)) {
            for (j in (-1..1)) {
                val current = Position(x + i, y + j)
                assertEquals(current, current.moveTo(target), "$current moving to $target")
            }
        }
    }

    @Test
    fun `move in a straight line`() {
        val (x, y) = getLocations()
        val target = Position(x, y)
        listOf(Pair(-2, 0), Pair(2, 0), Pair(0, -2), Pair(0, 2)).forEach {
            val current = Position(x + it.first, y + it.second)
            assertEquals(Position(x + it.first / 2, y + it.second / 2), current.moveTo(target), "$current moving to $target")
        }
    }

    @Test
    fun `move diagonally`() {
        val (x, y) = getLocations()
        val target = Position(x, y)
        listOf(Pair(-2, 1), Pair(2, 1), Pair(1, -2), Pair(1, 2), Pair(-2, -1), Pair(2, -1), Pair(-1, -2), Pair(-1, 2)).forEach {
            val current = Position(x + it.first, y + it.second)
            assertEquals(Position(x + it.first / 2, y + it.second / 2), current.moveTo(target), "$current moving to $target")
        }

    }

    private fun getLocations(): Pair<Int, Int> {
        val x = Random.nextInt()
        val y = Random.nextInt()
        return Pair(x, y)
    }
}