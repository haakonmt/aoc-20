package no.quist.aoc20.days

import no.quist.aoc20.Day

object Day5 : Day<List<Int>, Int>() {
    override fun createInput() = inputLines.map {
        var remainingRows = (0..127).toList()
        var remainingColumns = (0..7).toList()

        for (c in it) {
            when (c) {
                'F' -> remainingRows = remainingRows.take(remainingRows.size / 2)
                'B' -> remainingRows = remainingRows.takeLast(remainingRows.size / 2)
                'L' -> remainingColumns = remainingColumns.take(remainingColumns.size / 2)
                'R' -> remainingColumns = remainingColumns.takeLast(remainingColumns.size / 2)
            }
        }

        (remainingRows[0] * 8) + remainingColumns[0]
    }.sorted()

    override fun part1(input: List<Int>) = input.last()

    override fun part2(input: List<Int>) =
        input.windowed(2, 1)
            .first { it[1] - it[0] == 2 }
            .let { it[0] + 1 }
}
