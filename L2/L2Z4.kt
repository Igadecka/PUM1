package L2

fun safeParseAndClassify(input: String?): String {
    if (input.isNullOrEmpty()) {
        return "BRAK_DANYCH"
    }

    val num = input.toIntOrNull()

    if (num == null) {
        return "BRAK_DANYCH"
    }

    return if (num % 2 == 0) {
        "PARZYSTA"
    } else {
        "NIEPARZYSTA"
    }
}

fun main() {
    println(safeParseAndClassify(""))  // Brak
    println(safeParseAndClassify("4")) // Parzysta
    println(safeParseAndClassify("7")) // Mieparzysta
}