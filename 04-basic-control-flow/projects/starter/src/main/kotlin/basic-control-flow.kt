import kotlin.random.Random

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

    val yes: Boolean = true
    val no: Boolean = false

    // inferred
    val y = true
    val n = false


    val doesOneEqualTwo = (1 == 2)

    val doesOneNotEqualTwo = (1 != 2)

    val alsoTrue = !(1==2)

    val isOneGreaterThanTwo = (1 > 2)
    val isOneLessThanTwo = (1 < 2)


    val and = true && true
    val or = true || false


    // String equiality
    // in kotlin you can use the equality operator for strings while in java is recommended to use .equals()

    val guess = "dog"
    val dogEqualsCat = guess == "cat"

    // also works for lexicographical comparisons

    val order = "cat" < "dog"
    println(order)


    // Mini Exercises

    // Ex 1
    val myAge = 32
    val isTeenager = (myAge > 13) && (myAge < 19)
    println(isTeenager)

    // Ex 2
    val theirAge = 30
    val bothTeenagers = ((theirAge > 13) && (theirAge < 19)) && isTeenager
    println(bothTeenagers)

    // Ex 3
    val reader = "Ulises Uriel Verduzco Díaz"
    val author = "Richard Lucas"

    val authorIsReader = reader == author
    println(authorIsReader)

    // Ex 4
    val readerBeforeAuthor = reader < author
    println(readerBeforeAuthor)

    //The IF expression
    if(2>1){
        println("Yes, 2 is greater that 1")
    }

    val animal = "Fox"
    if(animal == "Cat" || animal == "Dog"){
        println("Animal is a house pet")
    } else{
        println("Animal is not a house pet")
    }

    val a = 5
    val b = 10

    val min = if(a < b) a else b
    val max = if(a > b) a else b

    // Short circuiting

    val name = "test"
    if ( 1 > 2 && name == "Matt Galloway" ){

        // the first expression "1>2" is false, so it won't evaluate to true for && operator
        // no matter if the second expression could be true
    }

    if( 1 < 2 || name == "Matt Galloway"){

        // the first expression is true, so it doesn't matter if the second expression is true or false
        // because we are using an || operator
    }

    // Encapsulating variables

    var hoursWorked = 45

    var price = 0
    if( hoursWorked > 40){
        val hoursOver40 = hoursWorked - 40
        price += hoursOver40 *50
        hoursWorked -= hoursOver40
    }

    price += hoursWorked * 25
    println(price)
    // println(hoursOver40) // val not found

    // Loops

    // while loop
    var sum = 1

    while (sum < 1000){
        println(sum)
        sum = sum + (sum + 1)
    }

    // do-while loop
    sum = 1
    do{
        println(sum)
        sum = sum + (sum + 1)
    } while(sum < 1000)

    // breaking out of a loop

    sum = 1
    while(true){ // runs forever
        println(sum)
        sum = sum + (sum + 1)
        if(sum >= 1000){
            break // breaks out
        }
    }

    // Mini Exercises

    // Ex 1

    var counter = 0
    while(counter < 10){
        println("counter is $counter")
        counter += 1
    }

    // Ex 2
    counter = 0
    var roll = 0

    do {
        roll = Random.nextInt(6)
        counter += 1
        println("$counter Rolls, Roll is $roll")
    } while (roll != 0)


}
