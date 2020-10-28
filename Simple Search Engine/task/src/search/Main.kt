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
                    val strategies = listOf<String>("ALL", "ANY", "NONE")
                    println()
                    println("Select a matching strategy: ALL, ANY, NONE")
                    val strategy = readLine()!!
                    if (strategy !in strategies) {
                        throw java.lang.Exception("Invalid strategy")
                    }
                    println()
                    println("Enter a name or email to search all suitable people.")
                    val query = readLine()!!
                    val queryList = query.split(" ")
                    val results: List<String> = doInvertedIndexSearchWithStrategy(people, index, queryList, strategy)
                    //var results: List<String> = doLinearSearch(people, query)
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
        val words = people[line].toUpperCase().split(" ")
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

/** linear search: no more used */
fun doLinearSearch(people: List<String>, query: String): List<String> {
    val results = mutableListOf<String>()
    for (i in people.indices) {
        if (people[i].toUpperCase().contains(query.toUpperCase())) {
            results.add(people[i])
        }
    }
    return results
}

fun doInvertedIndexSearchWithStrategy(people: List<String>, index: Map<String, MutableList<Int>>, queryList: List<String>, strategy: String): List<String> {
    val matchingLines = when (strategy) {
        "ALL" -> doInvertedIndexSearchAll(index, queryList)
        "ANY" -> doInvertedIndexSearchAny(index, queryList)
        "NONE" -> doInvertedIndexSearchNone(index, queryList)
        else -> mutableListOf<Int>()
    }
    val results = mutableListOf<String>()
    for (line in matchingLines) {
        results.add(people[line])
    }
    return results
}

fun doInvertedIndexSearchAll(index: Map<String, MutableList<Int>>, queryList: List<String>): List<Int> {
    var resultLines = mutableListOf<Int>()
    for (query in queryList) {
        val queryResult = doInvertedIndexSearch(index, query)
        if (resultLines.size == 0) {
            resultLines.addAll(queryResult)
        } else {
            resultLines = resultLines.intersect(queryResult).toMutableList()
        }
    }
    return resultLines
}

fun doInvertedIndexSearchAny(index: Map<String, MutableList<Int>>, queryList: List<String>): List<Int> {
    var resultLines = mutableListOf<Int>()
    for (query in queryList) {
        val queryResult = doInvertedIndexSearch(index, query)
        if (resultLines.size == 0) {
            resultLines.addAll(queryResult)
        } else {
            resultLines = resultLines.union(queryResult).toMutableList()
        }
    }
    return resultLines
}

fun doInvertedIndexSearchNone(index: Map<String, MutableList<Int>>, queryList: List<String>): List<Int> {
    val matchingLines = doInvertedIndexSearchAny(index, queryList)
    val allLines = getAllLines(index)
    return allLines.subtract(matchingLines).toList()
}

fun getAllLines(index: Map<String, MutableList<Int>>): List<Int> {
    val lines = mutableListOf<Int>()
    for (k in index.keys) {
        if (index[k] != null) {
            lines.addAll(index[k]!!)
        }
    }
    return lines
}

/** Returns the lines that match the query */
fun doInvertedIndexSearch(index: Map<String, MutableList<Int>>, query: String): List<Int> {
    val results = mutableListOf<Int>()
    for (k in index.keys) {
        if (query.toUpperCase() == k && index[k] != null) {
            for (line in index[k]!!) {
                results.add(line)
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
