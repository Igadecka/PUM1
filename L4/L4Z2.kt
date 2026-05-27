package L4.z2

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
                Random.nextInt(1,13),
                Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }
}


fun printSortedCosts(costs: List<Cost>) {
    val groupedCosts = costs
        .sortedBy { it.date }
        .groupBy { it.date.month}

    for ((month, monthlyCosts) in groupedCosts) {
        println(month)
        for (cost in monthlyCosts) {
            val day = cost.date.dayOfMonth.toString().padStart(2, '0')
            println("$day ${cost.type.name} ${cost.amount} zł")
        }
    }

}

fun main() {
    printSortedCosts(DataProvider.generalCosts)
}