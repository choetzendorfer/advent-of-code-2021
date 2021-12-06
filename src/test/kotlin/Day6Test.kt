import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day6Test {
    private val testInput = listOf(
        "3,4,3,1,2",
    )

    @Test
    fun testPart1() {
        val expectedValue = 5934

        Day6.part1(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testPart2() {
        val expectedValue = 26984457539

        Day6.part2(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }
}
