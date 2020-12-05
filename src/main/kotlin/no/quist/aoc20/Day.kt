package no.quist.aoc20

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

abstract class Day<Input, Output : Any>(private val lineDelimiter: String = System.lineSeparator()) {
    abstract fun createInput(): Input
    abstract fun part1(input: Input): Output
    abstract fun part2(input: Input): Output

    private val input: Input by lazy { createInput() }

    private val className = javaClass.simpleName

    val inputLines: List<String> by lazy {
        try {
            javaClass.getResource("/$className.txt")
                .readText()
                .split(lineDelimiter)
        } catch (ex: Exception) {
            println("Missing $className.txt in resources folder")
            emptyList()
        }
    }

    fun solveAndPrint() = runBlocking {
        listOf(::part1, ::part2)
            .mapIndexed { i, it -> GlobalScope.async { solvePartTimed(i + 1, it) } }
            .forEach { println(it.await()) }
    }

    private fun solvePartTimed(partIndex: Int, solver: (Input) -> Output): PartResult {
        val input = this.input // Load outside timer
        var result: Any = ""
        val time = measureTimeMillis {
            result = try {
                solver(input)
            } catch (err: NotImplementedError) {
                "NOT IMPLEMENTED"
            }
        }

        return PartResult(className, partIndex, result, time)
    }
}
