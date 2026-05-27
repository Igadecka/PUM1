package L2

fun check(preambleLength: Int, numbers: List<Int>): Int {
    for (index in preambleLength until numbers.size) {
        val currentNumber = numbers[index]

        val preamble = numbers.subList(index - preambleLength, index)

        var sum = false

        for (i in 0 until preamble.size) {
            for (j in i+1 until preamble.size) {
                val firstNumber = preamble[i]
                val secondNumber = preamble[j]

                if (firstNumber!=secondNumber && firstNumber+secondNumber == currentNumber) {
                    sum = true
                    break
                }
            }
            if (sum) break
        }

        if (!sum) {
            return currentNumber
        }
    }

    return -1
}

fun main() {
    println(check(3, listOf(1, 2, 3, 5, 7, 12, 30))) // 30
    println(check(2, listOf(1, 2, 3, 4, 5, 6)))       // 4
    println(check(5, listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576))) // 127
}