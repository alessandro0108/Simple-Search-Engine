import java.util.*

fun perimeter(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double, x4: Double = x3, y4: Double = y3): Double {
    val l1 = Math.hypot(x2 - x1, y2 - y1)
    val l2 = Math.hypot(x3 - x2, y3 - y2)
    val l3 = Math.hypot(x4 - x3, y4 - y3)
    val l4 = Math.hypot(x1 - x4, y1 - y4)
    return l1 + l2 + l3 + l4
}