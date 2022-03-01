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

    // Mini exercises

    // Ex 1, both are inferred as Int
    val age1 = 42
    val age2 = 21

    // Ex 2, inferred as Int but loses precision of 0.5
    val avg1 = (age1 + age2) / 2

    // Ex 3, converting terms to Double to avoid lose of precision and to infer type correctly
    val avg2 = (age1.toDouble() + age2.toDouble()) / 2


    // Strings

    val stringDog = "dog" // inferred type

    // Concatenation

    var message = "Hello "
    val name = "Joe"
    message += name
    println(message)

    // Characters
    val exclamationMark = '!' // Single quotes
    message += exclamationMark
    println(message)

    // Variable interpolation
    println("Hello my name is $name")
    println("One thirds is  ${1.0 / 3.0} as a decimal")

    // Multiline String

    val bigString = """
        |You can have a string
        |that contains multiple   
        |lines
        |by
        |doing this.
    """.trimMargin()

    println(bigString)


    // Mini exercises

    // Ex 1
    val firstName = "Ulises"
    val lastName = "Verduzco"

    // Ex 2
    val fullName = firstName + " " + lastName
    println(fullName)

    // Ex 3
    val myDetails = "Hello my name is $fullName"
    println(myDetails)


    // Pairs and Triples

    val coordinates: Pair<Int,Int> = Pair(2,3) // Explicit

    val coordinatesInferred = Pair(2,3) // Inferred

    val coordinatesWithTo = 2 to 3 // to operator

    val coordinatesDoubles = Pair(2.1, 3.5)

    val coordinatesMixed = Pair(2,3.1416)

    val x1 = coordinates.first
    val y1 = coordinates.second

    val (x2, y2) = coordinates // Deconstruction

    val coordinates3D = Triple(2,3,1)
    val (x3, y3, z3) = coordinates3D
    val z = coordinates3D.third


    // Mini Exercises

    //Ex 1

    val date : Triple<Int, Int, Int> = Triple(2,28, 2022)

    // Ex 2
    val (month, day, year) = date

    // Ex 3
    val (m, _, y) = date

    // Ex 4, values inside Pairs and Triples cannot be modified
    // So create a new Pair with modified data
    val newDate = Pair(m+1, y)

    // Any, Unit

    val anyNumber : Any = 42
    val anyString: Any = "42"
    // any is as Object in java, parent of all non-primitive types

    fun add(){ // implicit Unit return type, similar to "void" in java
        val result = 2 + 2
        println(result)
    }

    // Challenges

    // Ch 1
    val coordinates2 = Pair(2,3)
    val (row, column) = coordinates2

    val a = 4
    val b : Short = 100
    val c: Byte = 12
    val result = a + b - c

    val floatPI = Math.PI.toFloat()
    println(floatPI)

}