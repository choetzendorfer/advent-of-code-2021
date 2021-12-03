import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day3Test {
    private val testIntput = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010",
    )

    @Test
    fun testPart1() {
        val expectedValue = 198

        Day3.part1(testIntput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testPart2() {
        val expectedValue = 230

        Day3.part2(testIntput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testGetOxygenGeneratorRating() {
        val expectedValue = 23

        Day3.getOxygenGeneratorRating(testIntput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testGetCo2ScrubberRating() {
        val expectedValue = 10

        Day3.getCo2ScrubberRating(testIntput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }
}
