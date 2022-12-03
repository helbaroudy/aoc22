fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        input.forEachIndexed { index, entry ->
            val entries = entry.split(" ")
            val opponent = parse(entries[0], false)
            val mine = parse(entries[1], true)
            val result = mine.play(opponent).value + mine.value
            total += result
        }
        return total
    }

    fun parseMoveForDesiredResult(suggestion: String, opponent: Move) =
        when (decryptDesiredResult(suggestion)) {

            Result.LOSS -> when (opponent) {
                Move.ROCK____ -> Move.SCISSORS
                Move.PAPER___ -> Move.ROCK____
                Move.SCISSORS -> Move.PAPER___
            }

            Result.DRAW -> opponent


            Result.WIN_ -> when (opponent) {
                Move.ROCK____ -> Move.PAPER___
                Move.PAPER___ -> Move.SCISSORS
                Move.SCISSORS -> Move.ROCK____
            }


        }

    fun part2(input: List<String>): Int {
        var total = 0
        input.forEach { entry ->
            val entries = entry.split(" ")
            val opponent = parse(entries[0], false)
            val mine = parseMoveForDesiredResult(entries[1], opponent)
            val result = mine.play(opponent).value + mine.value
            total += result

        }
        return total
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day02_test")
    println(part2(testInput))
    check(part2(testInput) == 12)

    val input = readInput("input/Day02")
    println(part1(input))
    println(part2(input))
}


private fun parse(input: String, decrypt: Boolean): Move {
    val value = if (decrypt) decrypt(input) else input
    return when (value) {
        "A" -> Move.ROCK____
        "B" -> Move.PAPER___
        "C" -> Move.SCISSORS
        else -> error("Any")
    }
}

private fun decrypt(value: String) = when (value) {
    "X" -> "A"
    "Y" -> "B"
    "Z" -> "C"
    else -> error("Invalid")
}

fun decryptDesiredResult(value: String) =
    when (value) {
        "X" -> Result.LOSS
        "Y" -> Result.DRAW
        "Z" -> Result.WIN_
        else -> error("Err")
    }

enum class Move(val value: Int) {

    ROCK____(1),
    PAPER___(2),
    SCISSORS(3);

    fun play(opponent: Move): Result {
        return if (opponent == this) return Result.DRAW
        else when (this) {
            ROCK____ -> if (opponent == SCISSORS) Result.WIN_ else Result.LOSS
            SCISSORS -> if (opponent == PAPER___) Result.WIN_ else Result.LOSS
            PAPER___ -> if (opponent == ROCK____) Result.WIN_ else Result.LOSS
        }
    }
}

enum class Result(val value: Int) {

    LOSS(0),
    DRAW(3),
    WIN_(6),
}