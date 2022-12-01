package aoc

import java.io.File

class Day1Puzzle1(calorieList: String) {
    private val elves: List<Elf>

    init {
        elves = calorieList.split("\n\n")
            .map {
                val calories = it.split("\n").map { s -> s.toInt() }
                Elf(calories)
            }
    }

    fun elfCarryingMost() = elves.maxOf { it.totalCalories() }

    fun topThree(): Int {
        val elvesByCalories = elves.associateBy { it.totalCalories() }

        return elvesByCalories
            .keys
            .sortedDescending()
            .take(3)
            .map { elvesByCalories[it]!! }
            .sumOf { it.totalCalories() }
    }
}

data class Elf(val calories: List<Int>) {
    fun totalCalories() = calories.sum()
}

fun main() {
//    val file = "test"
    val file = "input"

    val input = File("inputs/day1/1/$file.txt").readText()
    val day1Puzzle1 = Day1Puzzle1(input)
    println(day1Puzzle1.elfCarryingMost())
    println(day1Puzzle1.topThree())
}