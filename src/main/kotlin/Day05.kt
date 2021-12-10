import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() {
    val input = readInput("Day05")

    println("[Day 5] Part 1: ${Day5.part1(input)}")
    println("[Day 5] Part 2: ${Day5.part2(input)}")
}

data class Point(val x: Int, val y: Int)
data class Line(val source: Point, val destination: Point)

object Day5 {
    fun part1(input: List<String>): Int {
        return calculateGrid(
            lines = getLines(input),
            includeDiagonals = false,
        ).sumOf { line ->
            line.count { it > 1 }
        }
    }

    fun part2(input: List<String>): Int {
        return calculateGrid(
            lines = getLines(input),
            includeDiagonals = true,
        ).sumOf { line ->
            line.count { it > 1 }
        }
    }

    private fun getLines(input: List<String>): List<Line> {
        return input.map { line ->
            val (source, destination) = line.split(" -> ")
            val (srcX, srcY) = source.split(",").map(String::toInt)
            val (destX, destY) = destination.split(",").map(String::toInt)
            Line(
                source = Point(srcX, srcY),
                destination = Point(destX, destY),
            )
        }
    }

    private fun calculateGrid(
        lines: List<Line>,
        includeDiagonals: Boolean,
    ): List<List<Int>> {
        val maxX = lines.maxOf { line -> max(line.source.x, line.destination.x) }
        val maxY = lines.maxOf { line -> max(line.source.y, line.destination.y) }
        val grid = List(maxY + 1) { MutableList(maxX + 1) { 0 } }

        lines.forEach { line ->
            val srcX = line.source.x
            val srcY = line.source.y
            val destX = line.destination.x
            val destY = line.destination.y
            val distanceX = abs(destX - srcX)
            val distanceY = abs(destY - srcY)

            when {
                distanceX == 0 -> {
                    // vertical movement
                    for (i in min(srcY, destY)..max(srcY, destY)) {
                        grid[i][srcX]++
                    }
                }
                distanceY == 0 -> {
                    // horizontal movement
                    for (i in min(srcX, destX)..max(srcX, destX)) {
                        grid[srcY][i]++
                    }
                }
                includeDiagonals -> {
                    // diagonal movement
                    val directionX = (destX - srcX).sign
                    val directionY = (destY - srcY).sign
                    for (i in 0..distanceX) {
                        grid[srcY + i * directionY][srcX + i * directionX]++
                    }
                }
            }
        }

        return grid
    }
}
