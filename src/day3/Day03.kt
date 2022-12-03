package day3

import readInput

fun main() {
    fun getType(compartment1: String, compartment2: String) = compartment1.first { compartment2.contains(it) }

    fun Char.toPriority() = if (this >= 'a') {
        this - 'a' + 1
    } else {
        this - 'A' + 1 + 26
    }

    fun part1(input: List<String>): Int = input.fold(0) { total, entry ->
        val compartment1 = entry.substring(0 until entry.length / 2)
        val compartment2 = entry.substringAfter(compartment1)
        val type = getType(compartment1, compartment2)
        total + type.toPriority()
    }

    fun getBadgeType(rucksack1: String, rucksack2: String, rucksack3: String) =
        rucksack1.first { rucksack2.contains(it) && rucksack3.contains(it) }

    fun part2(input: List<String>): Int {
        var total = 0
        for (i in input.indices step 3) {
            val badgeType = getBadgeType(input.get(i), input.get(i + 1), input.get(i + 2))
            total += badgeType.toPriority()
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    //part1(testInput)
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
