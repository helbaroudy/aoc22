fun main() {
    fun part1(input: List<String>) = input.fold(0) { total, entry ->
        val entries = entry.split(" ")
        val opponent = parse(entries[0])
        val mine = parse(entries[1])
        total + mine.play(opponent).value + mine.value
    }

    fun part2(input: List<String>) = input.fold(0) { total, entry ->
        val entries = entry.split(" ")
        val opponent = parse(entries[0])
        val mine = opponent.moveForResult(parseDesiredResult(entries[1]))
        total + mine.play(opponent).value + mine.value
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("input/Day02_test")
    println(part2(testInput))
    check(part2(testInput) == 12)

    val input = readInput("input/Day02")
    println(part1(input))
    println(part2(input))
}

private fun parse(value: String): Move {
    return when (value) {
        "A", "X" -> Move.ROCK____
        "B", "Y" -> Move.PAPER___
        "C", "Z" -> Move.SCISSORS
        else -> error("Err: $value")
    }
}

fun parseDesiredResult(value: String) = when (value) {
    "X" -> Result.LOSS
    "Y" -> Result.DRAW
    "Z" -> Result.WIN_
    else -> error("Err: $value")
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

    fun moveForResult(result: Result) = when (result) {
        Result.LOSS -> when (this) {
            ROCK____ -> SCISSORS
            PAPER___ -> ROCK____
            SCISSORS -> PAPER___
        }

        Result.DRAW -> this
        Result.WIN_ -> when (this) {
            ROCK____ -> PAPER___
            PAPER___ -> SCISSORS
            SCISSORS -> ROCK____
        }
    }

}

enum class Result(val value: Int) {
    LOSS(0),
    DRAW(3),
    WIN_(6),
}