package L2

fun <A> isSorted(lst: List<A>, order: (A, A) -> Boolean): Boolean {
    if (lst.size <= 1) {
        return true
    }

    for (index in 0 until lst.size - 1) {
        val currentItem = lst[index]
        val nextItem = lst[index + 1]

        if (!order(currentItem, nextItem)) {
            return false
        }
    }

    return true
}

fun main() {
    val test1 = isSorted(listOf(1, 2, 3, 4), {i: Int, j: Int -> i < j})
    println(test1)
    val test2 = isSorted(listOf(1, 1, 1, 1), {i: Int, j: Int -> i==j})
    println(test2)
    val test3 = isSorted(listOf("ahyyhh", "bkjn", "cnn", "duu"), {i: String, j: String -> i.first() < j.first()})
    println(test3)
}

