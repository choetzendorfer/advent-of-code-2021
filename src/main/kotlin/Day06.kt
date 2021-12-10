fun main() {
    val input = readInput("Day06")

    println("[Day 6] Part 1: ${Day6.part1(input)}")
    println("[Day 6] Part 2: ${Day6.part2(input)}")
}

object Day6 {
    fun part1(input: List<String>): Int {
        val fish = input[0].split(",").map(String::toInt).toMutableList()

        repeat(80) {
            var newFishCount = 0
            for (i in 0 until fish.size) {
                if (fish[i] == 0) {
                    newFishCount++
                    fish[i] = 6
                } else {
                    fish[i]--
                }
            }
            fish.addAll(List(newFishCount) { 8 })
        }

        return fish.count()
    }

    fun part2(input: List<String>): Long {
        val fish = input[0].split(",").map(String::toInt).toMutableList()
        val fishCountTillDaysLeftForBirth =
            Array(9) { 0L } // each index represents the days left till birth of new fish

        fish.forEach {
            fishCountTillDaysLeftForBirth[it]++
        }

        repeat(256) {
            val newFishCount = fishCountTillDaysLeftForBirth[0]
            val size = fishCountTillDaysLeftForBirth.size
            for (i in 1 until size) {
                fishCountTillDaysLeftForBirth[i - 1] = fishCountTillDaysLeftForBirth[i]
            }
            fishCountTillDaysLeftForBirth[size - 1] = 0

            fishCountTillDaysLeftForBirth[6] += newFishCount // parents
            fishCountTillDaysLeftForBirth[8] += newFishCount // new fish
        }

        return fishCountTillDaysLeftForBirth.sum()
    }
}
