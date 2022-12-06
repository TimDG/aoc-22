package aoc

import java.util.*

class Day5(input: String) {
    val instructions: List<Instruction>
    val board: Board

    init {
        val split = input.split("\n\n")
        instructions = split[1]
            .lines()
            .map { Instruction(it) }
        board = Board(split[0].lines())
    }

    fun executeInstructions() {
        instructions.forEach {
            board.execute(it)
        }
    }

    fun executeInstructions9001() {
        instructions.forEach {
            board.execute9001(it)
        }
    }
}

data class Board(private val stacks: List<Stack<Char>>) {
    fun execute(instruction: Instruction) {
        repeat(instruction.amount) {
            val crate = stacks[instruction.fromStack - 1].pop()
            stacks[instruction.toStack - 1].push(crate)
        }
    }

    fun execute9001(instruction: Instruction) {
        val cratesToMove = mutableListOf<Char>()
        repeat(instruction.amount) {
            cratesToMove.add(stacks[instruction.fromStack - 1].pop())
        }
        cratesToMove.reversed().forEach {
            stacks[instruction.toStack - 1].push(it)
        }
    }

    fun topCrates() = stacks.map { it.peek()!! }.joinToString("")

    companion object {
        operator fun invoke(lines: List<String>): Board {
            val stacksNeeded = lines
                .dropLastWhile { it.isEmpty() }
                .last()
                .split(Regex("\\s"))
                .last { it.isNotEmpty() }
                .toInt()

            val stacks = (0 until stacksNeeded).map {
                Stack<Char>()
            }
            lines
                .dropLastWhile { it.isEmpty() }
                .dropLast(1).reversed()
                .forEach {
                    for (i in 0..it.length step 4) {
                        if (it[i + 1] != ' ') {
                            stacks[i / 4].push(it[i + 1])
                        }
                    }
                }
            return Board(stacks)
        }
    }
}

data class Instruction(val fromStack: Int, val toStack: Int, val amount: Int) {
    companion object {
        operator fun invoke(line: String): Instruction {
            val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")
            val result = regex.find(line)!!.groupValues
            return Instruction(
                amount = result[1].toInt(),
                fromStack = result[2].toInt(),
                toStack = result[3].toInt()
            )
        }
    }
}

fun main() {
    val input = getInput("day5")
    val day5 = Day5(input)
    day5.executeInstructions()

    println(day5.board.topCrates())

    val day5Part2 = Day5(input)
    day5Part2.executeInstructions9001()
    println(day5Part2.board.topCrates())
}