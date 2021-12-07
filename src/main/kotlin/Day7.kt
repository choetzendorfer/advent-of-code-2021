import kotlin.math.abs

fun main() {
    val input = readInput("Day7")

    println("[Day 7] Part 1: ${Day7.part1(input)}")
    println("[Day 7] Part 2: ${Day7.part2(input)}")
}

object Day7 {
    fun part1(input: List<String>): Int {
        val crabPositions = input[0].split(",").map(String::toInt)
        val minPosition = crabPositions.minOrNull()!!
        val maxPosition = crabPositions.maxOrNull()!!

        return (minPosition..maxPosition).minOf { finalPosition ->
            crabPositions.sumOf { crabPosition ->
                abs(crabPosition - finalPosition)
            }
        }
    }

    fun part2(input: List<String>): Int {
        val crabPositions = input[0].split(",").map(String::toInt)
        val minPosition = crabPositions.minOrNull()!!
        val maxPosition = crabPositions.maxOrNull()!!

        return (minPosition..maxPosition).minOf { finalPosition ->
            crabPositions.sumOf { crabPosition ->
                val distance = abs(crabPosition - finalPosition)
                distance * (distance + 1) / 2
            }
        }
    }
}
