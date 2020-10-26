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

        val index = createInvertedIndex(people)

        var exit = false
        while (!exit) {
            when (getMenuOptions()) {
                "1" -> {
                    println()
                    println("Enter a name or email to search all suitable people.")
                    val query = readLine()!!
                    //var results: List<String> = doLinearSearch(people, query)
                    val results: List<String> = doInvertedIndexSearch(people, index, query)
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

private fun createInvertedIndex(people: List<String>): MutableMap<String, MutableList<Int>> {
    val index = mutableMapOf<String, MutableList<Int>>()
    for (line in people.indices) {
        val words = people[line].split(" ")
        for (word in words) {
            if (!index.containsKey(word)) {
                val indexList = mutableListOf<Int>()
                index[word] = indexList
            }
            index[word]?.add(line)
        }
    }
    return index
}

fun getMenuOptions(): String {
    println("=== Menu ===")
    println("1. Find a person")
    println("2. Print all people")
    println("0. Exit")
    return readLine()!!
}

fun doLinearSearch(people: List<String>, query: String): List<String> {
    val results = mutableListOf<String>()
    for (i in people.indices) {
        if (people[i].toUpperCase().contains(query.toUpperCase())) {
            results.add(people[i])
        }
    }
    return results
}

fun doInvertedIndexSearch(people: List<String>, index: Map<String, MutableList<Int>>, query: String): List<String> {
    val results = mutableListOf<String>()
    for (k in index.keys) {
        if (query == k && index[k] != null) {
            for (line in index[k]!!) {
                results.add(people[line])
            }
        }
    }
    return results
}

private fun printDataSet(results: List<String>) {
    for (j in results.indices) {
        println(results[j])
    }
}
