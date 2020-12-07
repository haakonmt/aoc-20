package no.quist.aoc20.days

import no.quist.aoc20.Day


object Day7 : Day<Map<String, Map<String, Int>>, Int>() {
    override fun createInput() = inputLines.mapNotNull {
        Regex("([a-z ]+) bags contain ([0-9a-z, ]+)\\.").find(it)?.groupValues?.let { (_, key, value) ->
            key to when (value) {
                "no other bags" -> emptyMap()
                else -> value.split(",").associate { part ->
                    val (_, v, k) = Regex("(\\d) (.*) bags?").find(part)!!.groupValues
                    k to v.toInt()
                }
            }
        }
    }.toMap()

    override fun part1(input: Map<String, Map<String, Int>>) =
            part1(input, "shiny gold", mutableSetOf()).size

    private fun part1(rules: Map<String, Map<String, Int>>, color: String, colors: MutableSet<String>): Set<String> {
        val parents = rules.filter { it.value.containsKey(color) }.keys
        while (colors.addAll(parents)) {
            colors.addAll(parents.flatMap {
                part1(rules, it, colors)
            })
        }
        return colors
    }

    private fun part2(rules: Map<String, Map<String, Int>>, color: String): Int {
        val it = rules[color]!!
        return it.values.sum() + it.map {
            it.value * part2(rules, it.key)
        }.sum()
    }

    override fun part2(input: Map<String, Map<String, Int>>) =
            part2(input, "shiny gold")
}