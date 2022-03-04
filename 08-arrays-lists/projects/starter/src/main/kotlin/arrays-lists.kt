import sun.security.util.ArrayUtil

/*
 * Copyright (c) 2021 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish, 
 * distribute, sublicense, create a derivative work, and/or sell copies of the 
 * Software in any work that is designed, intended, or marketed for pedagogical or 
 * instructional purposes related to programming, coding, application development, 
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works, 
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

fun main(args: Array<String>) {

    // 1. Creating arrays

    var evenNumbers : Array<Int> = arrayOf(2,4,6,8)
    println("$evenNumbers = ${evenNumbers}")

    val fiveFives = arrayOf(5, {5})
    println("$fiveFives")

    val vowels = arrayOf("a", "e", "i", "o", "u")
    println("$vowels")

    // 2. Arrays of primitives
    val oddNumbers = intArrayOf(1,3,5,7)
    println("$oddNumbers")

    val doubles = doubleArrayOf(1.1, 2.2, 3.3, 4.4)
    println("$doubles")

    val otherNumbers : IntArray= arrayOf(10).toIntArray()
    println("$otherNumbers")

    // 3. Arguments to main
    // open "Edit Configurations" and add some strings to the arguments

    // 4. Iterating over an array
    for(arg in args){
        println(arg)
    }

    args.forEach {
        println(it)
    }

    args.forEach { arg ->
        println(arg)
    }

    // 5. Lists, List is an interface implemented by ArrayList and LinkedList

    // 6. Creating lists
    val innerPlanets :List<String> = listOf("Mercury", "Venus", "Earth", "Mars")

    val innerPlanetsArrayList = arrayListOf("Mercury", "Venus", "Earth", "Mars") // implicit and as Arraylist

    val subscribers : List<String> = listOf() // empty lists cannot be inferred, they need explicit data types
    val followers = listOf<String>()

    // 7. Mutable Lists

    val outerPlanets = mutableListOf("Jupiter", "Saturn", "Uranus", "Neptune")

    val exoPlanets = mutableListOf<String>()

    // 8. Accessing elements

    // 9. Using properties and methods

    val players = mutableListOf<String>("Alice", "Bob", "Cindy", "Dan")

    println(players.isEmpty())

    if(players.size < 2){
        println("We need at least 2 players")
    } else {
        println("Let's start")
    }

    var currentPlayer = players.first()
    println(currentPlayer)

    var minPlayer = players.minOrNull()
    minPlayer.let {
        println("$minPlayer will start")
    }

    var maxPlayer = players.maxOrNull()
    maxPlayer.let {
        println("$maxPlayer will start")
    }

    // 10. Using indexing

    val firstPlayer = players[0]
    println("First player is $firstPlayer")

    val secondPlayer = players.get(1)
    println("second player is $secondPlayer")

    // 11. Using ranges to slice
    val upcomingPlayersList = players.slice(1..2)
    println("Upcoming players: ${upcomingPlayersList.joinToString(", ")}")

    // 12. Checking for an element
    fun isEliminated(player: String): Boolean{
       return player !in players
    }

    println(isEliminated("Bob"))
    println(players.slice(1..2).contains("Alice"))

    // 13. Modifying lists

    // 14. Appending elements
    players.add("Eli")

    players += "Gina"

    println(players.joinToString(", "))

    // recreate fixed-size array
    var array = arrayOf(1,2,3)
    array += 4
    println(array.joinToString(", "))

    // 15. Inserting elements
    players.add(5, "Frank")

    // 16. Removing elements
    val wasPlayerRemoved = players.remove("Gina") // returns boolean
    println("It is $wasPlayerRemoved that Gina was removed")

    val removedAtPlayer = players.removeAt(2) // returns element
    println("$removedAtPlayer was removed")

    // 17. Updating elements
    println(players.joinToString(", "))
    players[4] = "Franklin"
    println(players.joinToString(", "))

    players[3] = "Anna" // players.set(3, "Anna")
    players.sort()
    println(players.joinToString(", "))


    // 18. Iterating through a list
    val scores = listOf(2,2,8,6,1)

    for (player in players){
        println(player)
    }

    for((index, player) in players.withIndex()){
        println("${index + 1} = $player")
    }

    // 19. Nullability in collection types

    var nullableList : List<Int>? = listOf(1,2,3,4) // list might be null, elements in list: no
    nullableList = null

    var listOfNullables : List<Int?> = listOf(1, 2, null, 4) // elements might be null, list : no

    var nullableListOfNullables : List<Int?>? = listOf(1, 2, null, 4) // list and elements might be null
    nullableList = null

}

