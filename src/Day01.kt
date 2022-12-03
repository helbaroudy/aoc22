import java.util.Scanner

fun main() {
    // Execute, and copy paste the test input
    val list: MutableList<Int> = ArrayList()
    val sc = Scanner(System.`in`)
    var currentCalories = 0

    while (sc.hasNext()) {
        val current = sc.nextLine()
        val num = current.toIntOrNull()
        num?.let { currentCalories += it } ?: run {
            list += currentCalories
            currentCalories = 0
        }

    }
    list.sortByDescending { it }

    println("1: ${list[0]}")
    val result = list[0] + list[1] + list[2]
    println("2: $result")
}
