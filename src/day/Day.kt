package day

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("input/DayXX_test")
    part1(testInput)
    check(part1(testInput) == 1)

    val input = readInput("input/DayXX")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
