package L4.z3

import kotlin.random.*
import java.time.LocalDate
import java.time.Month

enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}

data class Cost (
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)


object DataProvider {
    val generalCosts = List(5) {
        Cost(
            CostType
                .values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025,
                Random.nextInt(1, 13),
                Random.nextInt(1, 28)
            ),
            Random.nextInt(5000)
        )
    }
}

sealed class MonthlyCosts
data object NoCosts : MonthlyCosts()
data class WithinLimit(val total: Int) : MonthlyCosts()
data class OverLimit(val total: Int, val exceededBy: Int) : MonthlyCosts()

fun classifyMonthlyCosts(costs: List<Cost>, month: Month, limit: Int): MonthlyCosts {
    val total = costs
        .filter { it.date.month == month }
        .sumOf { it.amount }

    return when {
        total == 0 -> NoCosts
        total <= limit -> WithinLimit(total)
        else -> OverLimit(total, total-limit)
    }
}

fun main() {
    val testCosts = listOf(
        Cost(CostType.REFUELING, LocalDate.of(2025, 1, 10), 300),
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 12), 50),
        Cost(CostType.SERVICE, LocalDate.of(2025, 2, 4), 1200)
    )

    println(classifyMonthlyCosts(testCosts, Month.JANUARY, 400))
    println(classifyMonthlyCosts(testCosts, Month.FEBRUARY, 1000))
    println(classifyMonthlyCosts(testCosts, Month.MARCH, 500))
}