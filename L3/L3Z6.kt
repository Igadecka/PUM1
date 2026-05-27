package L3

fun perm(items: List<Int>): List<List<Int>> {
    if (items.isEmpty()) return listOf(emptyList())

    return items.flatMap { selectedItem ->
        val remaining = items.filter { it != selectedItem }
        perm(remaining).map { subPerm -> listOf(selectedItem)+subPerm }
    }
}

fun main() {
    val result = perm(listOf(1, 2, 3))
    println(result)
}

