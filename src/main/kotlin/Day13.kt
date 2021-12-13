fun main() {
    val input = readInput("Day13")

    println("[Day 13] Part 1: ${Day13.part1(input)}")
    println("[Day 13] Part 2: ${Day13.part2(input)}")
}

object Day13 {
    private sealed interface FoldDirection {
        val value: Int

        data class Horizontal(override val value: Int) : FoldDirection
        data class Vertical(override val value: Int) : FoldDirection
    }

    fun part1(input: List<String>): Int {
        val dots = input
            .takeWhile { it.isNotBlank() }
            .map {
                val (x, y) = it.split(",").map(String::toInt)
                y to x
            }

        val paper = parsePaper(dots)
        val foldDirection = getFoldDirections(input).first()
        val foldedPaper = paper.fold(foldDirection)

        return foldedPaper.sumOf { row -> row.count { col -> col.isDot() } }
    }

    fun part2(input: List<String>) {
        val dots = input
            .takeWhile { it.isNotBlank() }
            .map {
                val (x, y) = it.split(",").map(String::toInt)
                y to x
            }

        val foldDirections = getFoldDirections(input)

        var paper = parsePaper(dots)
        foldDirections.forEach { foldDirection ->
            paper = paper.fold(foldDirection)
        }

        paper.forEach { row ->
            row.forEach { col ->
                if(col == "#") print("#") else print(" ")
            }
            println()
        }
    }

    private fun String.isDot(): Boolean {
        return this == "#"
    }

    private fun parsePaper(
        dots: List<Pair<Int, Int>>
    ): MutableList<MutableList<String>> {
        val maxX = dots.maxOf { it.second }
        val maxY = dots.maxOf { it.first }

        return MutableList(maxY + 1) { row ->
            MutableList(maxX + 1) { col ->
                if (dots.any { dot -> dot.first == row && dot.second == col }) {
                    "#"
                } else {
                    "."
                }
            }
        }
    }

    private fun getFoldDirections(input: List<String>) = input
        .takeLastWhile { it.isNotBlank() }
        .map {
            val (direction, value) = it.removePrefix("fold along ").split("=")
            when (direction) {
                "x" -> FoldDirection.Horizontal(value.toInt())
                "y" -> FoldDirection.Vertical(value.toInt())
                else -> throw IllegalArgumentException("Fold direction $direction not supported!")
            }
        }

    private fun MutableList<MutableList<String>>.fold(
        foldDirection: FoldDirection,
    ): MutableList<MutableList<String>> {
        val paper = this
        return when (foldDirection) {
            is FoldDirection.Horizontal -> {
                for (row in 0 until paper.size) {
                    for (col in 0 until foldDirection.value) {
                        if (paper[row][col].isDot() || paper[row][paper[row].size - 1 - col].isDot()) {
                            paper[row][col] = "#"
                        } else {
                            paper[row][col] = "."
                        }
                    }
                    paper[row] = paper[row].dropLast(foldDirection.value + 1).toMutableList()
                }
                paper
            }
            is FoldDirection.Vertical -> {
                for (row in 0 until foldDirection.value) {
                    for (col in 0 until paper[0].size) {
                        if (paper[row][col].isDot() || paper[paper.size - 1 - row][col].isDot()) {
                            paper[row][col] = "#"
                        } else {
                            paper[row][col] = "."
                        }
                    }
                }
                paper.dropLast(foldDirection.value + 1).toMutableList()
            }
        }
    }
}
