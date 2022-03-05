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

fun main() {


    // 1. Creating Maps

    var yearOfBirth = mapOf( //inmutable
        "Anna" to 1990,
        "Brian" to 1991,
        "Craig" to 1992,
        "Donna" to 1993
     )

    println(yearOfBirth)

    var namesAndScores = mutableMapOf( //mutable
        "Anna" to 2,
        "Brian" to 2,
        "Craig" to 8,
        "Donna" to 6,
    )

    println(namesAndScores)

    namesAndScores = mutableMapOf()

    var pairs = HashMap<String, Int>()

    pairs = HashMap<String, Int>(20)

    // 2. Accessing values

    // 3. Using the index operator

    namesAndScores = mutableMapOf( //mutable
        "Anna" to 2,
        "Brian" to 2,
        "Craig" to 8,
        "Donna" to 6,
    )
    println(namesAndScores["Anna"])
    println(namesAndScores["GReg"])

    // 4. Using properties and methods

    println(namesAndScores.get("Craig"))
    println(namesAndScores.isEmpty())
    println(namesAndScores.size)

    // 5. Modifying mutable maps

    // 6. Adding pairs

    val bobData = mutableMapOf(
        "name" to "Bob",
        "profession" to "CardPlayer",
        "country" to "USA"
    )

    bobData.put("state", "CA")
    bobData["city"] = "San Francisco"

    // 7. Removing data

    val oldCity = bobData.remove("city") // delete and get old value

    bobData.remove("state", "CA") // delete pair only if values match

    for( (player, score) in namesAndScores){
        println("$player - $score")
    }

    // 8. Running time for Map operations

    // 9. Hashcodes

    println("some string".hashCode())
    println(1.hashCode())
    println(false.hashCode())
    println(true.hashCode())

    // for performance hashmap should be used instead of map

    // 10. Sets

    // 11. creating sets

    val names = setOf("Anna", "Brian", "Craig", "Anna")
    println(names)

    val hashSet = HashSet<Int>()

    // 12. Sets from arrays
    val someArray = arrayOf(1,2,3,1)
    val someSet = mutableSetOf(*someArray)
    println(someSet)

    // 13. Accessing elements

    println(someSet.contains(1))
    println(4 in someSet)
    for (number in someSet){
        println(number)
    }

    // 14. Adding and removing elements
    someSet.add(5)

    val removedOne = someSet.remove(1)
    println(removedOne)

    println(someSet)
}