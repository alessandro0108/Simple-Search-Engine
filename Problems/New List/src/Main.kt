fun solution(numbers: List<Int>, number: Int): MutableList<Int> {
    val numbers2 = numbers.toMutableList()
    numbers2.add(number)
    return numbers2
}