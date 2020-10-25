package search

import java.io.File
import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    try {
        if (args.size != 2) {
            throw IllegalArgumentException("Invalid number of arguments")
        }
        if (args[0] != "--data") {
            throw IllegalArgumentException("Invalid option ${args[0]}")
        }
        val fileName = args[1]
        val file = File(fileName)
        val people = file.readLines()

        var exit = false
        while (!exit) {
            when (getMenuOptions()) {
                "1" -> {
                    println()
                    println("Enter a name or email to search all suitable people.")
                    val query = readLine()!!
                    val results: List<String> = doSearch(people, query)
                    if (results.isNotEmpty()) {
                        printDataSet(results)
                    } else {
                        println("No matching people found.")
                    }
                }
                "2" -> {
                    printDataSet(people)
                }
                "0" -> {
                    exit = true
                    println()
                    println("Bye!")
                }
                else -> {
                    println("Incorrect option! Try again.")
                }
            }
        }
    } catch (ex: Exception) {
        println(ex.message)
    }


}


private fun printDataSet(results: List<String>) {
    for (j in results.indices) {
        println(results[j])
    }
}

fun getMenuOptions(): String {
    println("=== Menu ===")
    println("1. Find a person")
    println("2. Print all people")
    println("0. Exit")
    return readLine()!!
}

fun doSearch(people: List<String>, query: String): List<String> {
    val results = mutableListOf<String>()
    for (i in people.indices) {
        if (people[i].toUpperCase().contains(query.toUpperCase())) {
            results.add(people[i])
        }
    }
    return results
}

