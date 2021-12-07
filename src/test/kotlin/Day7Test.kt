import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day7Test {
    private val testInput = listOf(
       "16,1,2,0,4,2,7,1,2,14",
    )

    @Test
    fun testPart1() {
        val expectedValue = 37

        Day7.part1(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }

    @Test
    fun testPart2() {
        val expectedValue = 168

        Day7.part2(testInput).apply {
            assertThat(this).isEqualTo(expectedValue)
        }
    }
}
