import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day11Test {
    private val testInput = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526",
    )

    @Test
    fun testPart1() {
        val expectedValue = 1656

        Day11.part1(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testPart2() {
        val expectedValue = 195

        Day11.part2(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }
}
