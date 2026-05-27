package L3

data class Point(val x: Int, val y: Int) {
    //p1 + p2
    operator fun plus(other: Point) = Point(this.x + other.x, this.y + other.y)
    //p1 += 1
    operator fun plus(value: Int) = Point(this.x + value, this.y + value)
    //p1 - p2
    operator fun minus(other: Point) = Point(this.x - other.x, this.y - other.y)
    //p1 * p2
    operator fun times(other: Point) = Point(this.x * other.x, this.y * other.y)
    //p1++
    operator fun inc() = Point(this.x + 1, this.y + 1)
    //p1--
    operator fun dec() = Point(this.x - 1, this.y - 1)
    //!p1
    operator fun not() = Point(-this.x, -this.y)
}

fun main() {
    var p1 = Point(1, 1)
    val p2 = Point(2, 2)

    println(p1 + p2) //(x=3, y=3)

    p1 += 1
    println(p1) //(x=2, y=2)

    p1 = Point(1, 1)
    println(p1 - p2) //(x=-1, y=-1)
    println(p1 * p2) //(x=2, y=2)

    p1++
    println(p1) //(x=2, y=2)

    p1 = Point(1, 1)
    p1--
    println(p1)  //(x=0, y=0)

    p1 = Point(1, 1)
    println(!p1) //(x=-1, y=-1)
}