package no.quist.aoc20.days

import no.quist.aoc20.Day
import java.lang.IllegalStateException

object Day9 : Day<List<Long>, Long>() {
    override fun createInput() = inputLines.mapNotNull(String::toLongOrNull)

    override fun part1(input: List<Long>): Long {
        return input.windowed(26, 1).first {
            var retVal = true
            outer@for (i in it.take(25)) {
                for (j in it.take(25)) {
                    if (j != i && i + j == it.last()) {
                        retVal = false
                        break@outer
                    }
                }
            }
            retVal
        }.last()
    }

    override fun part2(input: List<Long>): Long {
        val invalid = part1(input)
        for (i in input.indices) {
            var sum = input[i]
            var index = i
            while (sum < invalid) {
                index++
                if (index >= input.size) {
                    break
                }
                sum += input[index]
            }
            if (sum == invalid) {
                return input.subList(i, index).sorted().let {
                    it.first() + it.last()
                }
            }
        }

        throw IllegalStateException("Shouldn't reach this")
    }
}