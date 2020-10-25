fun square(value: Int, context: Any, continuation: (Int, Any) -> Unit) {
    val square = value * value
    continuation(square, context)
}