import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val lines = mutableListOf<String>()
    for (i in 0..3) {
        lines.add(scanner.nextLine())
    }
    if (lines[3] == "NO SEPARATOR") {
        println(concatenate(lines[0], lines[1], lines[2]))
    } else {
        println(concatenate(lines[0], lines[1], lines[2], lines[3]))
    }
}

fun concatenate(line1: String, line2: String, line3: String, separator: String = " "): String {
    return line1 + separator + line2 + separator + line3
}