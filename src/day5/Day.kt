package day5

import readInput

fun parseInput(input: List<String>): Pair<MutableList<String>, MutableList<Movement>> {
    val instructions = mutableListOf<Movement>()
    val stacks = MutableList((input[0].length + 1) / 4) { "" }
    for (entry in input) {
        if (entry.isNotEmpty() && !entry.startsWith("move")) {
            parseStacks(entry, stacks)
        } else if (entry.isNotEmpty()) {
            instructions.add(entry.parseMovement())
        }
    }
    return Pair(stacks, instructions)
}

private fun parseStacks(entry: String, stacks: MutableList<String>) {
    for ((current, i) in (1..entry.length step 4).withIndex()) {
        if (entry[i] != ' ' && !entry[i].isDigit()) {
            stacks[current] = stacks[current] + entry[i]
        }
    }
}

fun main() {
    fun moveOne(stacks: MutableList<String>, fromIndex: Int, toIndex: Int) {
        if (stacks[fromIndex].isNotEmpty()) {
            stacks[toIndex] = stacks[fromIndex][0] + stacks[toIndex]
            stacks[fromIndex] = stacks[fromIndex].removeRange(0, 1)
        }
    }

    fun moveMultiple(
        stacks: MutableList<String>,
        quantity: Int,
        toIndex: Int,
        fromIndex: Int,
    ) {
        stacks[toIndex] = stacks[fromIndex].substring(0, quantity) + stacks[toIndex]
        stacks[fromIndex] = stacks[fromIndex].removeRange(0, quantity)
    }

    fun applyInstructions(
        stacks: MutableList<String>,
        instructions: List<Movement>,
        batched: Boolean,
    ) {
        for (instruction in instructions) {
            val fromIndex = instruction.from - 1
            val toIndex = instruction.to - 1
            if (batched) {
                moveMultiple(stacks, instruction.quantity, toIndex, fromIndex)
            } else {
                for (i in 0.until(instruction.quantity)) {
                    moveOne(stacks, fromIndex, toIndex)
                }
            }
        }
    }


    fun part1(input: List<String>): String {
        val (stacks, instructions) = parseInput(input)
        applyInstructions(stacks, instructions, false)
        return stacks.fold("") { result, stack -> result + stack[0] }
    }


    fun part2(input: List<String>): String {
        val (stacks, instructions) = parseInput(input)
        applyInstructions(stacks, instructions, true)
        return stacks.fold("") { result, stack -> result + stack[0] }
    }

    val testInput = readInput("Day05_test")
    part1(testInput)
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    //part1(input)
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
    check(part1(input) == "SPFMVDTZT")
    check(part2(input) == "ZFSJBPRFP")

}

data class Movement(val quantity: Int, val from: Int, val to: Int)

fun String.parseMovement(): Movement {
    if (!startsWith("move")) error("Invalid movement")
    val split = this.split("move ", " from ", " to ")
    return Movement(split[1].toInt(), split[2].toInt(), split[3].toInt())
}

