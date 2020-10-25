fun main(args: Array<String>) {
    if (args.size != 3) {
        println("Invalid number of program arguments")
    } else {
        for (i in 0..2) {
            println("Argument ${i + 1}: ${args[i]}. It has ${args[i].length} characters")
        }
    }
}