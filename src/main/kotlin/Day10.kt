fun main() {
    val input = readInput("Day10")

    println("[Day 10] Part 1: ${Day10.part1(input)}")
    println("[Day 10] Part 2: ${Day10.part2(input)}")
}

object Day10 {
    private sealed interface ParseResult {
        data class AutoComplete(val closingChars: List<Char>) : ParseResult
        data class SyntaxError(val invalidChar: Char) : ParseResult
    }

    private val openingToClosing = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    private val errorToPoint = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )

    private val autoCompleteToPoint = mapOf(
        ')' to 1L,
        ']' to 2L,
        '}' to 3L,
        '>' to 4L,
    )

    fun part1(input: List<String>): Int {
        val lines = input.map { string -> string.map { char -> char } }

        return lines
            .map { it.parseLine() }
            .filterIsInstance<ParseResult.SyntaxError>()
            .sumOf { errorToPoint[it.invalidChar]!! }
    }

    fun part2(input: List<String>): Long {
        val lines = input.map { string -> string.map { char -> char } }

        val points = lines
            .map { it.parseLine() }
            .filterIsInstance<ParseResult.AutoComplete>()
            .map { automaticComplete ->
                automaticComplete.closingChars.map { closingChar ->
                    autoCompleteToPoint[closingChar]!!
                }.fold(0L) { acc, point ->
                    acc * 5L + point
                }
            }

        return points.sorted()[points.size / 2]
    }

    private fun List<Char>.parseLine(): ParseResult {
        val openingChars = mutableListOf<Char>()
        var iCol = 0

        while (iCol < this.size) {
            val currentChar = this[iCol]
            if (currentChar.isOpeningChar()) {
                openingChars.add(currentChar)
            } else {
                val lastOpeningTag = openingChars.removeLast()
                val expectedClosingTag = openingToClosing[lastOpeningTag]
                if (currentChar != expectedClosingTag) {
                    return ParseResult.SyntaxError(currentChar)
                }
            }
            iCol++
        }

        return ParseResult.AutoComplete(openingChars.map { openingToClosing[it]!! }.reversed())
    }

    private fun Char.isOpeningChar(): Boolean {
        return this in openingToClosing.keys
    }
}
