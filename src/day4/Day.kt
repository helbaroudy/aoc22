package day4

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        for (entry in input) {
            val rawElf1 = entry.split(",")[0]
            val rawElf2 = entry.split(",")[1]
            val elf1 = Pair(rawElf1.split("-")[0].toInt(), rawElf1.split("-")[1].toInt())
            val elf2 = Pair(rawElf2.split("-")[0].toInt(), rawElf2.split("-")[1].toInt())

            if (elf1.first >= elf2.first && elf1.second <= elf2.second ||
                elf2.first >= elf1.first && elf2.second <= elf1.second ) total++
        }
        return total
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day04_test")
    part1(testInput)
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
