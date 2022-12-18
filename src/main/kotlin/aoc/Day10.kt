package aoc

class Day10(input: String) {
    private val registerValues = mutableMapOf<Int, Int>()

    init {
        var cycle = 1
        var x = 1

        input.lines().forEach {
            if (it == "noop") {
                registerValues[cycle] = x
                cycle++
            } else if (it.startsWith("addx")) {
                registerValues[cycle] = x
                cycle++

                registerValues[cycle] = x
                cycle++
                x += it.substringAfterLast(" ").toInt()
            }
        }
    }

    fun getSimpleStrengths(): Int {
        return registerValues.filter { it.key == 20 || (it.key - 20).rem(40) == 0 }
            .map { it.value * it.key }
            .sum()
    }

    fun printScreen() {
        val line = mutableListOf<Char>()
        registerValues.forEach { (cycle, x) ->
            if ((cycle - 1).rem(40) in (x - 1..x + 1)) {
                line.add('X')
            } else {
                line.add('.')
            }
            if (cycle.rem(40) == 0) {
                println(line.joinToString(""))
                line.clear()
            }
        }
    }
}

fun main() {
    val day10 = Day10(getInput("day10"))
    println(day10.getSimpleStrengths())
    day10.printScreen()
}