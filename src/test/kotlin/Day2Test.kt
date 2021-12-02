import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class Day2Test {
    private val testIntput = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2",
    )

    @Test
    fun testPart1() {
        val expectedValue = 150

        Day2.part1(testIntput).apply {
            Assertions.assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testPart2() {
        val expectedValue = 900

        Day2.part2(testIntput).apply {
            Assertions.assertThat(this).isEqualTo(expectedValue)
        }
    }
}
