package aoc

class Day3(input: String) {
    private val rucksacks: List<Rucksack>

    init {
        rucksacks = input.lines().map { Rucksack(it) }
    }

    fun prioritySum(): Int {
        println(rucksacks)
        val common = rucksacks.map { it.commonType() }
        println(common)
        return common.sumOf {
            getPriority(it)
        }
    }

    private fun getPriority(it: Char) = if (it.isUpperCase()) {
        it.minus('A').plus(27)
    } else {
        it.minus('a').plus(1)
    }

    fun groupedPriorities() = rucksacks.indices.step(3).map {
        rucksacks.subList(it, it + 3)
    }.sumOf {
        val contents = it.map { r -> r.contents() }
        val badge = contents[0].intersect(contents[1])
            .intersect(contents[2])
            .first()
        getPriority(badge)
    }


}

data class Rucksack(val compartment1: List<Char>, val compartment2: List<Char>) {
    companion object {
        operator fun invoke(input: String): Rucksack {
            val totalItemCount = input.length
            val compartment1 = input.substring(0..totalItemCount / 2).map { it }
            val compartment2 = input.substring(totalItemCount / 2).map { it }
            return Rucksack(compartment1, compartment2)
        }
    }

    fun commonType() =
        compartment1.intersect(compartment2.toSet()).first()

    fun contents() = compartment1.union(compartment2)

}

fun main() {
    val input = getInput("day3")

    val day3 = Day3(input)
    println(day3.prioritySum())
    println(day3.groupedPriorities())
}