package L4.z1

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
                Random.nextInt(1,13),
                Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }
}

fun groupedCostMap(listOfCosts: List<Cost>): Map<Month, List<Cost>> {
    return listOfCosts.groupBy {it.date.month}
        .toSortedMap()
}

fun main() {
    val result= groupedCostMap(DataProvider.generalCosts)
    println(result)
}