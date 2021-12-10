fun main() {
    val input = readInput("Day03")

    println("[Day 3] Part 1: ${Day3.part1(input)}")
    println("[Day 3] Part 2: ${Day3.part2(input)}")
}

object Day3 {
    fun part1(input: List<String>): Int {
        var gammaRate = ""
        for (i in 0 until input.first().length) {
            gammaRate += input.getCharCountsForCol(i).getDominantChar()
        }

        return gammaRate.toDecimal() * gammaRate.swapBinary().toDecimal()
    }

    fun part2(input: List<String>): Int {
        return getOxygenGeneratorRating(input) * getCo2ScrubberRating(input)
    }

    internal fun getOxygenGeneratorRating(input: List<String>): Int {
        val mutableInput = input.toMutableList()
        var i = 0
        while (mutableInput.size != 1) {
            val charCounts = mutableInput.getCharCountsForCol(i)

            mutableInput.removeIf {
                if (charCounts.areCountsEqual()) {
                    it[i] != '1'
                } else {
                    it[i] != charCounts.getDominantChar()
                }
            }

            i++
        }

        return mutableInput.single().toDecimal()
    }

    internal fun getCo2ScrubberRating(input: List<String>): Int {
        val mutableInput = input.toMutableList()
        var i = 0
        while (mutableInput.size != 1) {
            val charCounts = mutableInput.getCharCountsForCol(i)

            mutableInput.removeIf {
                if (charCounts.areCountsEqual()) {
                    it[i] == '1'
                } else {
                    it[i] == charCounts.getDominantChar()
                }
            }

            i++
        }

        return mutableInput.single().toDecimal()
    }

    private fun List<String>.getCharCountsForCol(colIndex: Int): Map<Char, Int> {
        return groupingBy { it[colIndex] }.eachCount()
    }

    private fun Map<Char, Int>.areCountsEqual(): Boolean {
        return values.first() == values.last()
    }

    private fun Map<Char, Int>.getDominantChar(): Char {
        return maxByOrNull { it.value }!!.key
    }

    private fun String.toDecimal(): Int {
        return toInt(2)
    }

    private fun String.swapBinary(): String {
        return map {
            if (it == '1') '0' else '1'
        }.joinToString("")
    }
}
