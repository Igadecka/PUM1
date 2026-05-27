package L2

val <T> List<T>.head: T
    get() = this.first()

val <T> List<T>.tail: List<T>
    get() = this.drop(1)

fun main() {
    val List = listOf("apple", "banana", "pear")
    println("Oryginalna lista: $List")
    println("Pierwszy element: ${List.head}")
    println("Wszystkie elementy oprócz pierwszego: ${List.tail}")
}