package aoc

class Day4(input: String) {

    private val assignments: List<Assignment>

    init {
        assignments = input.lines().map { Assignment(it) }
    }

    fun countFullOverlaps() = assignments.count { it.fullyContained() }

    fun countOverlaps() = assignments.count{ it.overlaps() }

}

data class Assignment(val range1: IntRange, val range2: IntRange) {

    companion object {
        operator fun invoke(description: String): Assignment {
            val ranges = description.split(",")
                .map {
                    val from = it.substringBefore("-").toInt()
                    val to = it.substringAfter("-").toInt()
                    IntRange(from, to)
                }

            return Assignment(ranges[0], ranges[1])
        }
    }

    fun fullyContained() = range1.fullyContains(range2) || range2.fullyContains(range1)

    fun overlaps() = range1.overlaps(range2)
}

private fun IntRange.fullyContains(subRange: IntRange): Boolean {
    return this.contains(subRange.first) && this.contains(subRange.last)
}

private fun IntRange.overlaps(subRange: IntRange): Boolean {
    return subRange.find { this.contains(it) } != null
}

fun main() {
    val input = getInput("day4")

    val day4 = Day4(input)
    println(day4.countFullOverlaps())
    println(day4.countOverlaps())
}
