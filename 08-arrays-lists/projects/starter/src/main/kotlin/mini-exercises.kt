fun main (){

    // Ex 1. find and remove by index
    val players = mutableListOf<String>("Alice", "Bob", "Cindy", "Dan")
    players.add("Eli")
    players += "Gina"
    players.add(5, "Frank")

    var indexOfBob = players.indexOf("Bob")
    var bobRemoved = players.removeAt(indexOfBob)

    println("$bobRemoved was removed from his position")

    // Ex 2. names and scores


    val scores = listOf(2,2,8,6,1,1)

    for((index,player) in players.withIndex()){
        println("$player = ${scores[index]}")

    }
}