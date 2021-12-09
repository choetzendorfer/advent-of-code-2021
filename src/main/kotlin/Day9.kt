fun main() {
    val input = readInput("Day9")

    println("[Day 9] Part 1: ${Day9.part1(input)}")
    println("[Day 9] Part 2: ${Day9.part2(input)}")
}

object Day9 {
    private data class Point(val x: Int, val y: Int)
    private data class DataField(val pos: Point, val value: Int, var isLowPoint: Boolean = false)

    private var entries = listOf<List<DataField>>()

    fun part1(input: List<String>): Int {
        initEntries(input)
        return entries
            .flatten()
            .filter { it.isLowPoint }
            .sumOf { it.value + 1 }
    }

    fun part2(input: List<String>): Int {
        initEntries(input)
        return entries
            .asSequence()
            .flatten()
            .filter { it.isLowPoint }
            .map { it.parseBasin() }
            .sortedByDescending { it.size }
            .take(3)
            .fold(1) { acc, dataFields ->
                acc * dataFields.size
            }
    }

    private fun initEntries(input: List<String>) {
        entries = parseInput(input)
        markLowPoints(entries)
    }

    private fun parseInput(input: List<String>) = input.mapIndexed { rowIndex, string ->
        string.mapIndexed { colIndex, char ->
            DataField(
                pos = Point(colIndex, rowIndex),
                value = char.toString().toInt(),
            )
        }
    }

    private fun markLowPoints(entries: List<List<DataField>>) {
        val rowSize = entries.size
        val colSize = entries[0].size
        for (row in 0 until rowSize) {
            for (col in 0 until colSize) {
                val currentEntry = entries[row][col]
                val neighbors = currentEntry.getNeighbors()
                currentEntry.isLowPoint = neighbors.all { currentEntry.value < it.value }
            }
        }
    }

    private fun DataField.getNeighbors(): List<DataField> {
        val rowSize = entries.size
        val colSize = entries[0].size
        val rowIndex = pos.y
        val colIndex = pos.x

        val neighbors = mutableListOf<DataField>()
        if (rowIndex - 1 >= 0) {
            neighbors.add(entries[rowIndex - 1][colIndex])
        }
        if (rowIndex + 1 < rowSize) {
            neighbors.add(entries[rowIndex + 1][colIndex])
        }
        if (colIndex - 1 >= 0) {
            neighbors.add(entries[rowIndex][colIndex - 1])
        }
        if (colIndex + 1 < colSize) {
            neighbors.add(entries[rowIndex][colIndex + 1])
        }

        return neighbors
    }

    private fun DataField.parseBasin(): MutableList<DataField> {
        val basin = mutableListOf(this)

        var newPoints: List<DataField>
        do {
            newPoints = basin.flatMap { basinField ->
                basinField.getNeighbors().filter { it.value != 9 }
            }.distinct().filter { it !in basin }
            basin.addAll(newPoints)
        } while (newPoints.isNotEmpty())

        return basin
    }
}
