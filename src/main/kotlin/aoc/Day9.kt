package aoc

import kotlin.math.abs

class Day9(input: String, length: Int = 2) {
    val positions: Set<Position>
    private val visited = mutableSetOf<Position>()
    private var headPosition = Position(0, 0)
    private val snakePositions = mutableMapOf<Int, Position>()

    init {
        snakePositions[0] = headPosition
        (1 until length).forEach {
            snakePositions[it] = Position(0, 0)
        }
        visited.add(Position(0, 0))

        input.lines().forEach { it ->
            when (it.first()) {
                'U' -> repeat(getCount(it)) { move { p -> p.moveUp() } }

                'D' -> repeat(getCount(it)) { move { p -> p.moveDown() } }

                'R' -> repeat(getCount(it)) { move { p -> p.moveRight() } }

                'L' -> repeat(getCount(it)) { move { p -> p.moveLeft() } }

                else -> throw IllegalStateException("Wrong direction: must be U, D, L or R")
            }
        }

        positions = visited
    }

    private fun getCount(it: String) = it.substringAfterLast(" ").toInt()

    private fun move(function: (Position) -> Position) {
        headPosition = function.invoke(headPosition)
        snakePositions[0] = headPosition
        snakePositions.keys.drop(1)
            .forEach {
                snakePositions[it] = snakePositions[it]!!.moveTo(snakePositions[it-1]!!)
            }
        visited.add(snakePositions[snakePositions.keys.last()]!!)
    }
}

data class Position(val x: Int, val y: Int) {
    fun moveTo(targetPosition: Position): Position {
        return if (abs(targetPosition.x - x) > 1 || abs(targetPosition.y - y) > 1) {
            //Actually move
            val diffX = targetPosition.x - x
            val diffY = targetPosition.y - y


            if (diffX == 0) {
                return copy(y = y + diffY / abs(diffY))
            } else if (diffY == 0) {
                return copy(x = x + diffX / abs(diffX))
            } else {
                return copy(x = x + diffX / abs(diffX), y = y + diffY / abs(diffY))
            }

        } else {
            //Still close enough.
            this
        }
    }

    fun moveUp(): Position = copy(y = this.y + 1)
    fun moveDown(): Position = copy(y = this.y - 1)
    fun moveRight(): Position = copy(x = this.x + 1)
    fun moveLeft(): Position = copy(x = this.x - 1)
}

fun main() {
    val input = getInput("day9")
    println(Day9(input).positions.size)

    println(Day9(input, 10).positions.size)
}