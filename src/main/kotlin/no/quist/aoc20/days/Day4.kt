package no.quist.aoc20.days

import no.quist.aoc20.Day

object Day4 : Day<List<Map<String, String>>, Int>(removeBlankLines = false) {
    override fun createInput(): List<Map<String, String>> {
        val passports = mutableListOf<Map<String, String>>()
        val currentPassport = mutableListOf<String>()

        for (line in inputLines) {
            if (line.isNotBlank()) {
                currentPassport += line
            } else {
                passports += currentPassport
                    .joinToString(" ")
                    .split(" ")
                    .associate {
                        it.split(":").let { (key, value) -> key to value }
                    }

                currentPassport.clear()
            }
        }

        return passports
    }

    override fun part1(input: List<Map<String, String>>) = input.count {
        it.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
    }

    private infix fun String?.isYearInRange(yearRange: IntRange) =
        this?.toInt()?.let { it in yearRange } == true

    override fun part2(input: List<Map<String, String>>) = input.count {
        it["byr"] isYearInRange 1920..2002 &&
                it["iyr"] isYearInRange 2010..2020 &&
                it["eyr"] isYearInRange 2020..2030
                it["hgt"]?.let { height ->
                    val value = height.takeWhile(Char::isDigit).toInt()
                    when(height.takeLast(2)) {
                        "in" -> value in 59..76
                        "cm" -> value in 150..193
                        else -> false
                    }
                } == true &&
                it["hcl"]?.let(Regex("#([0-9a-f]{6})")::matches) == true &&
                it["ecl"] in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") &&
                it["pid"]?.let { pid -> pid.length == 9 && pid.all(Char::isDigit) } == true
    }
}
