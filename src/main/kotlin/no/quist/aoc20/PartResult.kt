package no.quist.aoc20

data class PartResult(val day: String, val part: Int, val result: Any, val time: Long) {
    override fun toString(): String = """
            $day - part $part
                Result: $result
                Time: ${time}ms

        """.trimIndent()
}
