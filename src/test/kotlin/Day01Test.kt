import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day01Test {
    private val testIntput = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263,
    )

    @Test
    fun testPart1() {
        val expectedIncreaseCount = 7

        Day1.part1(testIntput).apply {
            assertThat(this).isEqualTo(expectedIncreaseCount)
        }
    }

    @Test
    fun testPart2() {
        val expectedIncreaseCount = 5

        Day1.part2(testIntput).apply {
            assertThat(this).isEqualTo(expectedIncreaseCount)
        }
    }
}
