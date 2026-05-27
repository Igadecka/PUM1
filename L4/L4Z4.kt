package L4.z4

import kotlin.random.*
import java.time.LocalDate

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


interface CostFormatter {
    fun format(cost: Cost): String
}

object PlCostFormatter : CostFormatter {
    override fun format(cost: Cost): String {
        val day = cost.date.dayOfMonth.toString().padStart(2, '0')
        return "$day ${cost.type.name} ${cost.amount} zł"
    }
}

fun formatCosts(costs: List<Cost>, formatter: CostFormatter): String {
    return costs
        .sortedBy { it.date }
        .map { formatter.format(it) }
        .joinToString("\n")
}

fun main() {
    val testCosts = listOf(
        Cost(CostType.PARKING, LocalDate.of(2025, 1, 15), 30),
        Cost(CostType.SERVICE, LocalDate.of(2025, 1, 5), 900)
    )

    println(formatCosts(testCosts, PlCostFormatter))
}