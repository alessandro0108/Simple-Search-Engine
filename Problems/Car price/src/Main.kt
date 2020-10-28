import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val parameter: String = scanner.nextLine()
    val value: String = scanner.nextLine()
    val price = when (parameter) {
        "old" -> {
            getCarValue(20000, old = value.toInt())
        }
        "passed" -> {
            getCarValue(20000, passed = value.toInt())
        }
        "speed" -> {
            getCarValue(20000, speed = value.toInt())
        }
        "auto" -> {
            getCarValue(20000, auto = value == "1")
        }
        else -> getCarValue(20000)
    }
    println(price)
}

fun getCarValue(initialPrice: Int, old: Int = 5, passed: Int = 100000, speed: Int = 120, auto: Boolean = false): Int {
    return initialPrice - old * 2000 + 100 * (speed - 120) - 200 * (passed / 10000) + if (auto) 1500 else 0
}