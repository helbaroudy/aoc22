package day5

import readInput
import java.util.Queue
import java.util.ArrayDeque
import java.util.LinkedList

fun main() {
    fun part1(input: List<String>): String {
        val rawStacks = mutableListOf<String>()
        val instructions = mutableListOf<Movement>()
        val stacks = MutableList<LinkedList<Char>>((input[0].length + 1) / 4) { LinkedList() }
        for (entry in input) {
            if (!entry.startsWith("move") && (entry.isNotEmpty())) {
                rawStacks.add(entry)
                for ((current, i) in (1..entry.length step 4).withIndex()) {
                    if (entry[i] != ' ') stacks[current].add(entry[i])
                }
            } else if (entry.isNotEmpty()) instructions.add(entry.parseMovement())
        }


        for (instruction in instructions) {

            for (i in 0.until(instruction.quantity)) {
                if (stacks[instruction.from - 1].isNotEmpty()) {
                    val element = stacks[instruction.from - 1].element()
                    stacks[instruction.to - 1].addFirst(element)
                    stacks[instruction.from - 1].remove()
                }
            }
        }

        var result = ""
        for (stack in stacks) {
            result += stack[0]
        }
        return result
    }

    fun part2(input: List<String>): String {
        return "---"
    }

    val testInput = readInput("Day05_test")
    part1(testInput)
     check(part1(testInput) == "CMZ")
   //  check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    //part1(input)
    println("Part 1: ${part1(input)}")
    //  println("Part 2: ${part2(input)}")

}

data class Movement(val quantity: Int, val from: Int, val to: Int)

fun String.parseMovement(): Movement {
    if (!startsWith("move")) error("Invalid movement")
    val split = this.split("move ", " from ", " to ")
    return Movement(split[1].toInt(), split[2].toInt(), split[3].toInt())
}

