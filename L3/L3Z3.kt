package L3

fun suma(numbers: List<Int>): Int {
    return numbers
        .filter { it>0 }
        .sum()
}

fun main() {
    val input = listOf(1, -4, 12, 0, -3, 29, -150)
    println(suma(input))
}