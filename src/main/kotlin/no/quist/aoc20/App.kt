package no.quist.aoc20

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.quist.aoc20.days.*
import java.time.LocalDate

val days = listOf(Day1, Day2, Day3, Day4, Day5)

fun main() {
    runCurrentDay()
}

@Suppress("UNUSED")
private fun runAllDays() = runBlocking {
    days.map { GlobalScope.launch { it.solveAndPrint() } }.forEach { it.join() }
}

@Suppress("UNUSED")
private fun runCurrentDay() {
    days[LocalDate.now().dayOfMonth - 1].solveAndPrint()
}

@Suppress("UNUSED")
private fun runSpecifiedDay(day: Day<*, *>) = day.solveAndPrint()
