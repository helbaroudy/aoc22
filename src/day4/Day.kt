package day4

import readInput

typealias Section = Pair<Int, Int>

fun main() {
    fun Section.contains(elf: Section) = this.first >= elf.first && this.second <= elf.second

    fun Section.overlaps(elf: Section) = this.first >= elf.first && this.first <= elf.second

    fun parse(entry: String): Pair<Section, Section> {
        val rawElf1 = entry.split(",")[0]
        val rawElf2 = entry.split(",")[1]
        val elf1 = Section(
            rawElf1.split("-")[0].toInt(),
            rawElf1.split("-")[1].toInt()
        )
        val elf2 = Section(
            rawElf2.split("-")[0].toInt(),
            rawElf2.split("-")[1].toInt()
        )
        return Pair(elf1, elf2)
    }

    fun day4(input: List<String>, fullyContains: Boolean): Int {
        var total = 0
        for (entry in input) {
            val (elf1, elf2) = parse(entry)
            if (fullyContains) {
                if (elf1.contains(elf2) || elf2.contains(elf1)) total++
            } else if (elf1.overlaps(elf2) || elf2.overlaps(elf1)) {
                total++
            }
        }
        return total
    }

    fun part1(input: List<String>) = day4(input, true)
    fun part2(input: List<String>) = day4(input, false)

    val input = readInput("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
