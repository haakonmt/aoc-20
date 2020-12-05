package no.quist.aoc20.days

import no.quist.aoc20.Day

/**
 * @author  Håkon Meyer Tørnquist <haakon.t@gmail.com>
 *          Date: 05.12.2020 13.09.
 * @version 1.0
 */
object Day3 : Day<List<String>, Int>() {
    override fun createInput() = inputLines

    override fun part1(input: List<String>) = input.calculateNumberOfTrees(3, 1)

    override fun part2(input: List<String>) =
        input.calculateNumberOfTrees(1, 1) *
                input.calculateNumberOfTrees(3, 1) *
                input.calculateNumberOfTrees(5, 1) *
                input.calculateNumberOfTrees(7, 1) *
                input.calculateNumberOfTrees(1, 2)

    private fun List<String>.calculateNumberOfTrees(dx: Int, dy: Int): Int {
        var y = 0
        var x = 0
        var treeCount = 0
        while (y < this.size) {
            if (this[y][x % this[y].length] == '#') {
                treeCount++
            }
            y += dy
            x += dx
        }
        return treeCount
    }
}
