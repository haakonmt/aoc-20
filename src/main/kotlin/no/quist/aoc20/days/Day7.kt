package no.quist.aoc20.days

import no.quist.aoc20.Day


object Day7 : Day<Map<String, Map<String, Int>>, Int>() {
    override fun createInput() = inputLines.mapNotNull {
        Regex("([a-z ]+) bags contain ([0-9a-z, ]+)\\.").find(it)?.groupValues?.let { (_, key, value) ->
            key to when (value) {
                "no other bags" -> emptyMap()
                else -> value.split(",").associate { part ->
                    Regex("(\\d) (.*) bags?").find(part)!!.groupValues.let { (_, v, k) ->
                        k to v.toInt()
                    }
                }
            }
        }
    }.toMap()

    override fun part1(input: Map<String, Map<String, Int>>): Int {
        fun part1(color: String, colors: MutableSet<String>): Set<String> {
            val parents = input.filter { it.value.containsKey(color) }.keys
            while (colors.addAll(parents)) {
                colors.addAll(parents.flatMap {
                    part1(it, colors)
                })
            }
            return colors
        }

        return part1("shiny gold", mutableSetOf()).size
    }

    override fun part2(input: Map<String, Map<String, Int>>): Int {
        fun part2(color: String): Int {
            val it = input[color]!!
            return it.values.sum() + it.map {
                it.value * part2(it.key)
            }.sum()
        }

        return part2("shiny gold")
    }
}