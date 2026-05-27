package L3

fun countElements(listOfLists: List<List<String>>): Map<String, Int> {
    return listOfLists
        .flatten()
        .groupBy { it }
        .mapValues { it.value.size }
}

fun main() {
    val input = listOf(listOf("a", "b", "c"), listOf("c", "d", "f"), listOf("d", "f", "g"))
    val result = countElements(input)
    println(result)
}