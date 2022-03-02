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

    // Ranges
    val closedRange = 0..5

    val halfOpenRange = 0 until 5

    val decreasingRange = 5 downTo 0



    // For loops

    val count = 10

    var sum = 0

    for (i in 1 ..count){
        sum += i
        println("Start of iteration $i: i = $i, sum = $sum")
    }

    // Repeat, no loop constant

    sum = 1
    var lastSum = 0
    repeat(10){
        val temp = sum
        sum += lastSum
        lastSum = temp

        println("lastSum: = $lastSum, sum = $sum")
    }

    // Step in loops
    sum = 0
    for (i in 1..count step 2){
        sum += 1
        println("Start of iteration $i: i = $i, sum = $sum")
    }

    sum = 0
    for (i in count downTo  1 step 2){
        sum += 1
        println("Start of iteration $i: i = $i, sum = $sum")
    }

    // Continue
    // skipping even rows from a matrix

    sum = 0
    for (row in 0 until 8){
        if(row % 2 == 0){
            continue
        }
        for (column in 0 until 8){
            sum += row*column
        }
    }

    println("sum of odd rows = $sum")

    // sum all numbers with column <= row

    sum = 0

    rowLoop@ for(row in 0 until 8){
        columnLoop@ for(column in 0 until  8){
            if(column == row ){
                continue@rowLoop
            }
            sum += row* column
        }
    }
    println("sum of diagonal numbers = $sum")

    // When

    val number = Random.nextInt(10)

    when(number){
        0 -> println("is Zero!")
        else -> println("is not Zero!")
    }

    // Works with more data types
    val animal = listOf("Cat", "Dog", "Shark").random()

    when(animal){
        "Dog", "Cat" -> println("Animal is a house pet")
        else -> println("Animal is not a house pet")
    }

    // return something

    val numberName = when(number){
        2 -> "Two"
        4 -> "Four"
        6 -> "Six"
        8 -> "Eight"
        10 -> "Ten"
        else -> {
            println("Number is not known")
            "Unknown"
        }
    }

    println(numberName)

    // Complex choice
    val hourOfTheDay = Random.nextInt(24)
    var timeOfTheDay = ""

    timeOfTheDay = when(hourOfTheDay){
        0, 1, 2, 3, 4, 5 -> "Early morning"
        6, 7, 8, 9, 10, 11 -> "Morning"
        12, 13, 14, 15, 16 -> "Afternoon"
        17, 18, 19 -> "Evening"
        20, 21, 22, 23 -> "Night"
        else -> {
            "Unknown"
        }
    }

    println("hour $hourOfTheDay is $timeOfTheDay")

    // Ranges

    timeOfTheDay = when(hourOfTheDay){
        in 0 .. 5 -> "Early morning"
        in 6 .. 11 -> "Morning"
        in 12 .. 16 -> "Afternoon"
        in 17 .. 19 -> "Evening"
        in 20 .. 23 -> "Night"
        else -> {
            "Unknown"
        }
    }

    println("hour $hourOfTheDay is $timeOfTheDay")


    // Conditions

    when {
        number % 2 == 0 -> println("even")
        else -> println("odd")
    }
}