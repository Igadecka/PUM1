package L3

fun srt(words: List<String>): List<Pair<String, List<String>>> {
    return words
        .filter { word -> word.length%2==0 }
        .groupBy { word -> word.take(1) }
        .map { entry -> Pair(entry.key, entry.value) }
        .sortedBy { pair -> pair.first }
}

fun main() {
    val input = listOf(
        "cherry",
        "blueberry",
        "citrus",
        "apple",
        "apricot",
        "banana",
        "coconut"
    )
    println(srt(input))
}