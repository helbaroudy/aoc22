package day2

import readInput

fun main() {
    fun parse(value: String): Move {
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

    fun part1(input: List<String>) = input.fold(0) { total, entry ->
        val entries = entry.split(" ")
        val opponent = parse(entries[0])
        val mine = parse(entries[1])
        total + mine.play(opponent).score + mine.score
    }

    fun part2(input: List<String>) = input.fold(0) { total, entry ->
        val entries = entry.split(" ")
        val opponent = parse(entries[0])
        val mine = opponent.moveForResult(parseDesiredResult(entries[1]))
        total + mine.play(opponent).score + mine.score
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class Result(val score: Int) {
    LOSS(0),
    DRAW(3),
    WIN_(6),
}
enum class Move(val score: Int) {
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