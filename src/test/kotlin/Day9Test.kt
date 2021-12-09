import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day9Test {
    private val testInput = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678",
    )

    @Test
    fun testPart1() {
        val expectedValue = 15

        Day9.part1(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testPart2() {
        val expectedValue = 1134

        Day9.part2(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }
}
