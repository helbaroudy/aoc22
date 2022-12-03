fun main() {
    fun getType(compartment1: String, compartment2: String): Char {
        var solution = ' '
        for (item in compartment1) {
            if (compartment2.contains(item)) {
                solution = item
            }
        }
        return solution
    }

    fun getPriorityValue(type: Char) = if (type >= 'a') {
        type - 'a' + 1
    } else {
        type - 'A' + 1 + 26
    }

    fun part1(input: List<String>): Int {
        var total = 0
        for (entry in input) {
            val compartment1 = entry.substring(0 until entry.length / 2)
            val compartment2 = entry.substringAfter(compartment1)
            check(compartment1.length == compartment2.length)
            val type = getType(compartment1, compartment2)
            total += getPriorityValue(type)
        }
        return total
    }

    fun getBadgeType(ruckstack1: String, ruckstack2: String, ruckstack3: String): Char {
        var solution = ' '
        for (item in ruckstack1) {
            if (ruckstack2.contains(item) && ruckstack3.contains(item)) {
                solution = item
            }
        }
        return solution
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (i in 0 until input.size step 3) {
            val badgeType = getBadgeType(input.get(i), input.get(i + 1), input.get(i + 2))
            total += getPriorityValue(badgeType)
            println(badgeType)

        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day03_test")
    //part1(testInput)
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("input/Day03")
    println(part1(input))
    println(part2(input))
}
