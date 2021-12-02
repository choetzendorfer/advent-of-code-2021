fun main() {
    val input = readInput("Day2")

    println("[Day 2] Part 1: ${Day2.part1(input)}")
    println("[Day 2] Part 2: ${Day2.part2(input)}")
}

object Day2 {
    fun part1(input: List<String>): Int {
        var horizontal = 0
        var vertical = 0

        input.forEach { line ->
            val (direction, movementValue) = line.parseDirectionAndValue()

            when (direction) {
                "forward" -> horizontal += movementValue
                "up" -> vertical -= movementValue
                "down" -> vertical += movementValue
            }
        }

        return vertical * horizontal
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var vertical = 0
        var aim = 0

        input.forEach { line ->
            val (direction, movementValue) = line.parseDirectionAndValue()

            when (direction) {
                "forward" -> {
                    horizontal += movementValue
                    vertical += movementValue * aim
                }
                "up" -> aim -= movementValue
                "down" -> aim += movementValue
            }
        }

        return vertical * horizontal
    }

    private fun String.parseDirectionAndValue(): Pair<String, Int> {
        val splitLine = split(" ")
        val direction = splitLine.first().trim()
        val movementValue = splitLine.last().trim().toInt()

        return direction to movementValue
    }
}
