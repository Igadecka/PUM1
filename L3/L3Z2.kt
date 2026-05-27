package L3

fun addToBoolean(): Map<Int, Boolean> {
    val result = mutableMapOf<Int, Boolean>()
    for (number in 1..20) {
        result[number]=(number%2==0)
    }
    return result
}

fun main() {
    println(addToBoolean())
}