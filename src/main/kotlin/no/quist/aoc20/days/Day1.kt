package no.quist.aoc20.days

import no.quist.aoc20.Day

object Day1 : Day<List<Int>, Int>() {
    override fun createInput() = inputLines.map { it.toInt() }

    override fun part1(input: List<Int>): Int {
        val map = mutableMapOf<Int, Int>()
        for (i in input) {
            if (i < 2020) {
                if (map.containsKey(i)) {
                    return i * map[i]!!
                } else {
                    map[2020 - i] = i
                }
            }
        }

        throw IllegalStateException()
    }

    override fun part2(input: List<Int>): Int {
        // A bit naive, but oh well
        for (i in input) {
            if (i < 2020) {
                for (j in input) {
                    if (j != i && j < 2020) {
                        for (k in input) {
                            if (k != j && k != i && k + i + j == 2020) {
                                return k * i * j
                            }
                        }
                    }
                }
            }
        }

        throw IllegalStateException()
    }
}
