package no.quist.aoc20.days

import no.quist.aoc20.Day

data class Day2Command(val min: Int, val max: Int, val char: Char, val password: String)

object Day2 : Day<List<Day2Command>, Int>() {
    override fun createInput() = inputLines.mapNotNull {
        Regex("([\\d]+)-([\\d]+) ([a-z]): ([a-z]+)")
            .find(it)
            ?.destructured
            ?.let { (_, min, max, char, password) ->
                Day2Command(min.toInt(), max.toInt(), char.first(), password)
            }
    }

    override fun part1(input: List<Day2Command>) = input.count {
        it.password.count { c -> c == it.char } in it.min..it.max
    }

    override fun part2(input: List<Day2Command>) = input.count {
        (it.password[it.min - 1] == it.char) xor (it.password[it.max - 1] == it.char)
    }
}
