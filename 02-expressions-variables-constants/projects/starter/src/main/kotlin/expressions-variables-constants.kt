import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.random.nextInt

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

const val myAge = 32

fun main() {
    println(2 + 6)
    println(10 - 2)
    println(4 * 2)
    println(16 / 2)

    println(28 % 10)
    println(28.0 % 10.0)
    println("%.0f".format(28.0 % 10.0))

    println(1 shl 3)
    println(32 shr 2)


    var averageAge: Double = myAge.toDouble()
    averageAge = 30.0

    println(averageAge)

    val testNumber = 1
    val evenOdd = testNumber % 2

    println(evenOdd)

    var answer = 0
    answer += 1
    answer += 10
    answer *= 10
    answer shr 3
    println(answer)


    // Ex 1
    val exercises = 9
    var exercisesSolved = 0

    exercisesSolved += 1

    // Ex 2
    var age = 16
    println(age)
    age = 30
    println(age)

    exercisesSolved += 1

    // Ex 3
    val a : Int = 46
    val b : Int = 10

    val answer1: Int = (a * 100) + b
    println(answer1)

    val answer2: Int = (a * 100) + (b * 100)
    println(answer2)

    val answer3: Int = (a * 100) + (b / 10)
    println(answer3)

    exercisesSolved += 1

    // Ex 4
    val exercise6 = (5*3)-(4/(2*2))
    println(exercise6)

    exercisesSolved += 1

    // Ex 5
    val amount1 : Double = 10.0
    val amount2 : Double = 3.0
    val average = (amount1 + amount2) / 2
    println(average)

    exercisesSolved += 1

    // Ex 6
    val fahrenheit = 100

    val celcius =(fahrenheit -32 ) / 1.8

    println(celcius)
    exercisesSolved += 1

    // Ex 7
    val position = Random.nextInt(64)
    println(position)
    val row = (position / 8 ) + 1
    println(row - 1)
    val column = 8 - (row*8) % position
    println(column)

    exercisesSolved += 1

    // Ex 8
    val degrees : Double = 30.0
    val radians = degrees * (PI / 180)
    println(radians)

    exercisesSolved += 1

    // Ex 9
    val x1 = 5.0
    val y1 = 2.0

    val x2 = 15.0
    val y2 = 5.0

    val distance : Double =   sqrt((x2 - x1).pow(2) +(y2 - y1).pow(2))

    println(distance)

    val slope : Double =   (x2 - x1) / (y2 - y1)

    println(slope)

    exercisesSolved += 1
    println(exercisesSolved)

    println("Challenge completed!")
}