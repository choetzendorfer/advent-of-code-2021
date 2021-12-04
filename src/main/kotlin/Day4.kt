fun main() {
    val input = readInput("Day4")

    println("[Day 4] Part 1: ${Day4.part1(input)}")
    println("[Day 4] Part 2: ${Day4.part2(input)}")
}

data class Field(
    val value: Int,
    var marked: Boolean = false,
)

object Day4 {
    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map(String::toInt)
        val playBoards = getPlayBoards(input)

        for (currentNumber in numbers) {
            for (board in playBoards) {
                board.markNumber(currentNumber)
                if (board.hasBingo()) {
                    return board.getSumOfUnmarkedNumbers() * currentNumber
                }
            }
        }

        throw RuntimeException("No board has bingo")
    }

    fun part2(input: List<String>): Int {
        val numbers = input[0].split(",").map(String::toInt)
        val playBoards = getPlayBoards(input)

        var lastBingoBoardToBingoNumber: Pair<List<List<Field>>, Int>? = null
        for (currentNumber in numbers) {
            for (board in playBoards) {
                if (!board.hasBingo()) {
                    board.markNumber(currentNumber)
                    if (board.hasBingo()) {
                        lastBingoBoardToBingoNumber = board to currentNumber
                    }
                }
            }
        }

        val (lastBoard, bingoNumber) = lastBingoBoardToBingoNumber!!
        return lastBoard.getSumOfUnmarkedNumbers() * bingoNumber
    }

    private fun getPlayBoards(input: List<String>) = input
        .drop(1)
        .chunked(6)
        .map { line ->
            line.filter { it.isNotBlank() }
                .map {
                    val numbers = it.split(" ").filter { it.isNotBlank() }
                    numbers.map { number ->
                        Field(number.toInt())
                    }
                }
        }

    private fun List<List<Field>>.markNumber(number: Int) {
        map { row ->
            row.singleOrNull { it.value == number }?.let { field ->
                field.marked = true
            }
        }
    }

    private fun List<List<Field>>.hasBingo(): Boolean {
        return hasAnyRowBingo() || hasAnyColumnBingo()
    }

    private fun List<List<Field>>.hasAnyRowBingo(): Boolean {
        return any { row ->
            row.all { it.marked }
        }
    }

    private fun List<List<Field>>.hasAnyColumnBingo(): Boolean {
        return this[0].indices.any { col ->
            all { row -> row[col].marked }
        }
    }

    private fun List<List<Field>>.getSumOfUnmarkedNumbers(): Int {
        return sumOf { row ->
            row.sumOf { col ->
                if (!col.marked) col.value else 0
            }
        }
    }
}
