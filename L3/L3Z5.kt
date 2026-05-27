package L3

fun evenPositiveSquare(numbers: List<Int>): List<Int> {
    return numbers
        .filterIndexed { index, value -> index%2!=0 && value>0 }
        .map { it*it }
}

fun main() {
    val input = listOf(1, 2, 3, 5, -6, -1, -1, 2, 3)
    val result = evenPositiveSquare(input)
    println(result)
}

