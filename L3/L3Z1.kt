package L3

fun findDuplicates(numbers: List<Int>): List<Int> {
    val num = mutableSetOf<Int>()
    val duplicates = mutableSetOf<Int>()

    for (number in numbers) {
        if (!num.add(number)) {
            duplicates.add(number)
        }
    }
    return duplicates.sorted()
}

fun main() {
    val lst = listOf(0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3, 3, 3)
    println(findDuplicates(lst))
}