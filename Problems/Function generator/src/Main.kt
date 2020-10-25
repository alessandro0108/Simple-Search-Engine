// TODO: provide three functions here
fun identity(value: Int): Int {
    return value
}

fun half(value: Int): Int {
    return value / 2
}

fun zero(value: Int): Int {
    return 0
}

fun generate(functionName: String): (Int) -> Int {
    return when (functionName) {
        "identity" -> ::identity
        "half" -> ::half
        "zero" -> ::zero
        else -> ::zero
    }
}