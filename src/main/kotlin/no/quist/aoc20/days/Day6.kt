package no.quist.aoc20.days

import no.quist.aoc20.Day

object Day6 : Day<List<String>, Int>(System.lineSeparator() + System.lineSeparator()) {
    override fun createInput() = inputLines

    override fun part1(input: List<String>) = input.map {
        it.replace(System.lineSeparator(), "").toSet().size
    }.sum()

    override fun part2(input: List<String>) = input.map {
        it.split(System.lineSeparator()).map { s -> s.toSet() }
    }.map {
        it.fold(it.first()) { acc, curr ->
            acc.intersect(curr)
        }.size
    }.sum()
}