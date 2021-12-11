fun main() {
    val input = readInput("Day11")

    println("[Day 11] Part 1: ${Day11.part1(input)}")
    println("[Day 11] Part 2: ${Day11.part2(input)}")
}

object Day11 {
    private data class Position(val row: Int, val col: Int)
    private data class Octopus(val position: Position, var energyLevel: Int)

    private var octopuses = listOf<List<Octopus>>()

    fun part1(input: List<String>): Int {
        octopuses = parseOctopuses(input)

        var flashes = 0
        repeat(100) {
            performStep()
            flashes += performFlashes()
        }

        return flashes
    }

    fun part2(input: List<String>): Int {
        octopuses = parseOctopuses(input)

        var step = 0
        do {
            performStep()
            performFlashes()
            step++
        } while (!octopuses.all { octopusRow -> octopusRow.all { it.energyLevel == 0 } })

        return step
    }

    private fun parseOctopuses(input: List<String>) = input
        .mapIndexed { row, line ->
            line.mapIndexed { col, energyLevel ->
                Octopus(Position(row, col), energyLevel.toString().toInt())
            }
        }

    private fun performStep() {
        octopuses.forEach { octopusRow ->
            octopusRow.forEach { octopus ->
                octopus.energyLevel++
            }
        }
    }

    private fun performFlashes(): Int {
        var flashCount = 0
        while (octopuses.any { octopusRow -> octopusRow.any { it.energyLevel > 9 } }) {
            octopuses.forEach { octopusRow ->
                octopusRow.forEach { octopus ->
                    if (octopus.energyLevel > 9) {
                        octopus.energyLevel = 0
                        flashCount++
                        octopus.getNeighbors().filter { it.energyLevel != 0 }.forEach { it.energyLevel++ }
                    }
                }
            }
        }
        return flashCount
    }

    private fun Octopus.getNeighbors(): List<Octopus> {
        val neighbors = mutableListOf<Octopus>()
        for (row in -1..1) {
            for (col in -1..1) {
                if (row != 0 || col != 0) {
                    val rowIndex = position.row + row
                    val colIndex = position.col + col
                    if (rowIndex >= 0 && rowIndex < octopuses.size) {
                        if (colIndex >= 0 && colIndex < octopuses[rowIndex].size) {
                            neighbors.add(octopuses[rowIndex][colIndex])
                        }
                    }
                }
            }
        }
        return neighbors
    }
}
