package aoc

class Day8(input: String) {

    private val visible = mutableSetOf<Tree>()

    private val trees: List<List<Tree>> = input.lines().mapIndexed { i, line ->
        line.mapIndexed { j, c -> Tree(c.digitToInt(), j, i) }
    }

    fun getVisibleTrees(): Int {
        trees.forEach {
            countVisible(it)
            countVisible(it.reversed())
        }

        transpose(trees).forEach {
            countVisible(it)
            countVisible(it.reversed())
        }
        return visible.size
    }

    private fun transpose(trees: List<List<Tree>>): List<List<Tree>> {
        val transposed = mutableListOf<MutableList<Tree>>()
        transposed.addAll(trees.map { mutableListOf() })
        trees.forEachIndexed { i, line ->
            line.forEachIndexed { j, tree ->
                transposed[j].add(i, tree)
            }
        }
        return transposed
    }

    private fun countVisible(line: List<Tree>) {
        var highest = Tree(-1, -1, -1)
        line.forEach {
            if (it > highest) {
                highest = it
                visible.add(it)
            }
        }
    }

    fun scenicScore(): Int {
        return visible.map {
            it to calculateScenicScore(it)
        }.maxBy { it.second }
            .second
    }

    private fun calculateScenicScore(tree: Tree): Int {
        return countUp(tree) * countDown(tree) * countLeft(tree) * countRight(tree)
    }

    private fun countUp(tree: Tree): Int {
        var y = tree.y
        var count = 0
        while (y > 0) {
            y--
            count++
            if (trees[y][tree.x] >= tree) {
                break
            }
        }
        return count
    }
    private fun countDown(tree: Tree): Int {
        var y = tree.y
        var count = 0
        while (y < trees.size - 1) {
            y++
            count++
            if (trees[y][tree.x] >= tree) {
                break
            }
        }
        return count
    }

    private fun countLeft(tree: Tree): Int {
        var x = tree.x
        var count = 0
        while (x > 0) {
            x--
            count++
            if (trees[tree.y][x] >= tree) {
                break
            }
        }
        return count
    }
    private fun countRight(tree: Tree): Int {
        var x = tree.x
        var count = 0
        while (x < trees[0].size - 1) {
            x++
            count++
            if (trees[tree.y][x] >= tree) {
                break
            }
        }
        return count
    }
}

data class Tree(val height: Int, val x: Int, val y: Int): Comparable<Tree> {
    override fun compareTo(other: Tree): Int {
        return height.compareTo(other.height)
    }
}

fun main() {
    val input = getInput("day8")

    val day8 = Day8(input)
    println(day8.getVisibleTrees())
    println(day8.scenicScore())
}