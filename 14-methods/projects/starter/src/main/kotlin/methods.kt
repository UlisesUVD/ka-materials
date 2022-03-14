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


    // 1. Turning a function into a method


    // function outside class
    val monthsRemaining = monthsUntilWinterBreak(SimpleDate1("July"))
    println("Months until winter break: $monthsRemaining")

    // method in class
    val date2 = SimpleDate2("October")
    val monthsRemaining2 = date2.monthsUntilWinterBreak(date2)
    println("Months until winter break: $monthsRemaining2")


    // 2. Introducing 'this'

    val date3 = SimpleDate3("September")
    val monthsRemaining3 = date3.monthsUntilWinterBreak(date3)
    println("Months until winter break: $monthsRemaining3")

    // Mini exercise
    val date4 = SimpleDate4("January")
    val monthsRemaining4 = date4.monthsUntilWinterBreak
    println("Months until winter break: $monthsRemaining4")

    // 3. Object Methods
    val factorial = MyMath.factorial(3)
    println(
        factorial
    )

    // Mini exercise
    val nTriangle = MyMath.nTriangle(4)
    println(
        nTriangle
    )

    // 4. Extension methods
    // see ext.kt

    // 5. Companion object extensions
    // see ext.kt

}

// 1.

val months = arrayOf(
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
)

class SimpleDate1(var month: String)

fun monthsUntilWinterBreak(from: SimpleDate1): Int {
    return months.indexOf("December") - months.indexOf(from.month)
}

class SimpleDate2(var month: String) {
    fun monthsUntilWinterBreak(from: SimpleDate2): Int {
        return months.indexOf("December") - months.indexOf(from.month)
    }
}

class SimpleDate3(var month: String) {
    fun monthsUntilWinterBreak(from: SimpleDate3): Int {
        return months.indexOf("December") - months.indexOf(this.month)
    }
}


// Mini exercise

class SimpleDate4(var month: String) {
    val monthsUntilWinterBreak: Int
        get() {
            return months.indexOf("December") - months.indexOf(this.month)
        }
}

// 4.
class MyMath{
    companion object{
        fun factorial(number: Int): Int {
            return (1..number).fold(1){acc, i -> acc*i }
        }

        fun nTriangle(number: Int): Int{
            return (1..number).reduce { acc, i ->  acc+i}
        }
    }
}