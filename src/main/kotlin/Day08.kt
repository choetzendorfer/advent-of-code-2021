fun main() {
    val input = readInput("Day08")

    println("[Day 8] Part 1: ${Day8.part1(input)}")
    println("[Day 8] Part 2: ${Day8.part2(input)}")
}

object Day8 {
    fun part1(input: List<String>): Int {
        val output = input
            .map { it.split(" | ")[1] }
            .flatMap { it.split(" ") }

        val uniqueDigitSegmentCounts = setOf(2, 3, 4, 7)
        return output.count { activated ->
            activated.length in uniqueDigitSegmentCounts
        }
    }

    fun part2(input: List<String>): Int {
        val test = input.map { it.split(" | ")[0] }.map { it.split(" ") }
        val output = input.map { it.split(" | ")[1] }.map { it.split(" ") }

        return test.mapIndexed { index, strings ->
            val segmentMap = strings.parseSegmentMap()
            val outputData = output[index]

            outputData.map { data ->
                val outputCharacters = data.map { it }
                segmentMap
                    .entries
                    .single {
                        it.value.length == data.length
                                && it.value.all { char -> char in outputCharacters }
                    }.key
            }.joinToString("")
        }.map(String::toInt).sum()
    }

    private fun List<String>.parseSegmentMap(): Map<Int, String> {
        val map = mutableMapOf<Int, String>()
        map[1] = single { it.length == 2 }
        map[4] = single { it.length == 4 }
        map[7] = single { it.length == 3 }
        map[8] = single { it.length == 7 }

        map[3] = single { it.length == 5 && it.hasAtLeastSameActivatedSegmentsAs(map[7]!!) }
        map[9] = single { it.length == 6 && it.hasAtLeastSameActivatedSegmentsAs(map[3]!!) }
        map[0] = single {
            it.notIn(map) && getMatchingActivatedSegmentCount(it, map[9]!!) == 5 && getMatchingActivatedSegmentCount(
                source = it,
                target = map[7]!!,
            ) == 3
        }
        map[6] = single { it.notIn(map) && getMatchingActivatedSegmentCount(it, map[8]!!) == 6 }
        map[2] = single {
            it.notIn(map) && getMatchingActivatedSegmentCount(it, map[6]!!) == 4 && getMatchingActivatedSegmentCount(
                source = it,
                target = map[4]!!,
            ) == 2
        }
        map[5] = single { it.notIn(map) }
        return map
    }


    private fun String.notIn(map: Map<Int, String>): Boolean {
        return this !in map.values
    }

    private fun String.hasAtLeastSameActivatedSegmentsAs(target: String): Boolean {
        val targetChars = target.map { it }
        val sourceChars = map { it }
        return sourceChars.all { it in targetChars } || targetChars.all { it in sourceChars }
    }

    private fun getMatchingActivatedSegmentCount(source: String, target: String): Int {
        val targetChars = target.map { it }
        return source.count { it in targetChars }
    }
}
