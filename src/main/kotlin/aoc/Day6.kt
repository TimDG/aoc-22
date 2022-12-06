package aoc

fun findStartMarker(input: String) = findMarker(input, 4)

fun findStartMessageMarker(input: String) = findMarker(input, 14)

private fun findMarker(input: String, markerLength: Int): Int {
    repeat(input.length - markerLength) {
        val sub = input.substring(it until it + markerLength).toSet()
        if (sub.size == markerLength) {
            return it + markerLength
        }
    }
    throw IllegalStateException("No start marker")
}

fun main() {
    val input = getInput("day6")

    println(findStartMarker(input))
    println(findStartMessageMarker(input))
}