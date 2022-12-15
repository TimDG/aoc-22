package aoc

class Day7 (input: String) {
    private val rootDir = Dir("/")

    init {
        var currentDir = rootDir
        for (line in input.lines()) {
            if (line.startsWith("$ cd")) {
                //command
                val dirName = line.substringAfterLast(" ")
                if (dirName == "/") {
                    currentDir = rootDir
                } else if (dirName == "..") {
                    currentDir = currentDir.parentDir!!
                }  else {
                    currentDir.getSubDir(dirName).also { currentDir = it }
                }
            } else if (line.startsWith("$ ls")) {
                //start directory listing.
            } else if (line.startsWith("dir")) {
                //file listing: dir
                val dirName = line.substringAfterLast(" ")
                currentDir.getSubDir(dirName)
            } else {
                //File
                val split = line.split(" ")
                currentDir.addFile(split[1], split[0].toLong())
            }
        }
    }

    fun sizes(): Long {
        return allDirs().map { it.name to it.totalSize() }
            .filter { it.second <= 100_000L }
            .sumOf { it.second }
    }

    private fun allDirs(): MutableList<Dir> {
        val dirs = mutableListOf<Dir>()
        dirs.addAll(getSubDirs(rootDir))
        return dirs
    }

    private fun getSubDirs(dir: Dir): Collection<Dir> {
        val res = mutableListOf<Dir>()
        res.add(dir)
        res.addAll(dir.subDirs().map { getSubDirs(it) }.flatten())
        return res
    }

    fun findCleanupDir(): Long {
        val diskSpace = 70000000L
        val spaceNeeded = 30000000L
        val freeSpace = diskSpace - rootDir.totalSize()

        val toClean = spaceNeeded - freeSpace

        val (dir, size) = allDirs().map { it to it.totalSize() }
            .filter { it.second >= toClean }
            .minBy { it.second }

        println("$dir -> $size")
        return size
    }
}

data class Dir(val name: String, val parentDir: Dir? = null) {
    private val subDirs = mutableListOf<Dir>()
    private val files = mutableListOf<LocalFile>()

    fun subDirs() = subDirs.toList()

    fun getSubDir(dirName: String): Dir {
        val dir = subDirs.find { it.name == dirName }
        if (dir != null) return dir

        val newDir = Dir(dirName, this)
        subDirs.add(newDir)
        return newDir
    }

    fun addFile(name: String, size: Long) {
        files.add(LocalFile(name, size))
    }

    fun totalSize(): Long = files.sumOf { it.size } + subDirs.sumOf { it.totalSize() }
}

data class LocalFile(val name: String, val size: Long)


fun main() {
    val input = getInput("day7")
    val day7 = Day7(input)

    println(day7.sizes())
    println(day7.findCleanupDir())
}