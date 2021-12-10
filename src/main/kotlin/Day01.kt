fun main() {
    val input = readInput("Day01").map { it.toInt() }

    println("[Day 1] Part 1: ${Day1.part1(input)}")
    println("[Day 1] Part 2: ${Day1.part2(input)}")
}

object Day1 {
    fun part1(input: List<Int>): Int {
        return input.countIncrements()
    }

    fun part2(input: List<Int>): Int {
        return input
            .windowed(size = 3)
            .map { it.sum() }
            .countIncrements()
    }

    private fun List<Int>.countIncrements(): Int {
        return windowed(2).count { (first, second) -> second > first }
    }
}
