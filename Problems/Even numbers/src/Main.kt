fun solution(numbers: List<Int>) {
    for (number in numbers) {
        if (number % 2 == 0) {
            print("$number ")
        }
    }
}