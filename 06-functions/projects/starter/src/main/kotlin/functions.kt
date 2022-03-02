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
    printMyName()
    printMultipleOfFive(2)
    printMultipleOf(10, 20)

    // 3. Parameter named arguments
    printMultipleOf(andValue = 10, multiplier = 50 )

    // 4. Parameter Default values
    printMultipleOfWithDefaults()
    printMultipleOfWithDefaults(4)
    printMultipleOfWithDefaults(andValue = 2)

    // 5. Return values
    val result = multiply(10, 20)
    val results = multiplyAndDivide(4,2)
    val inferredResult = multiplyInferred(4,2)

    // 6. Parameters and values
    incrementAndPrint(10)

    // 7. Overloading
    var incremented1 = getValue(10)
    var incremented2 = getValue(10, 2)
    println("increment $incremented1")
    println("increment $incremented2")

    // 8. Functions as variables
    var function = ::add
    function(4,2)
    function = ::subtract
    function(4,2)
    function = ::add
    printResult(function, 4, 2)
}


// 1. Function declaration
fun printMyName(){
    println("My name")
}

// 2. Function parameters
fun printMultipleOfFive(value: Int){
    println("$value * 5 = ${value*5}")
}

fun printMultipleOf(multiplier: Int, andValue: Int){
    println("$multiplier * $andValue = ${multiplier*andValue}")
}

// 4. Parameter Default values
fun printMultipleOfWithDefaults(multiplier: Int = 10, andValue: Int = 1){
    println("$multiplier * $andValue = ${multiplier*andValue}")
}

// 5. Return values

fun multiply(multiplier: Int, andValue: Int): Int{
    return multiplier * andValue
}

fun multiplyAndDivide(number: Int, factor: Int): Pair<Int, Int>{
    return Pair(number * factor, number / factor)
}

fun multiplyInferred(number: Int, factor: Int) : Int = number * factor

// 6. Parameters as values
fun incrementAndPrint(value: Int){
    // value += 1 // parameters are constants, cannot be reassigned
    var newValue = value
    println(value)
    println(newValue)
}

// 7. Overloading
fun getValue(value: Int) : Int{
    return value + 1
}

fun getValue(value: Int, increment: Int) : Int{
    return value + increment
}


fun getValue(value: String) : String{
    return value
}

// cannot overload, because it differs only in return type, not in parameters
/*
fun getValue(value: String) : Int{
    return value.toInt()
}
*/

// 8. Functions as variables
fun add(a: Int, b: Int): Int {
    return a+b
}

fun subtract(a: Int, b: Int): Int {
    return a-b
}

// you can pass functions as parameters
fun printResult( function: (Int, Int) -> Int, a: Int, b: Int){
    val result = function(a,b)
    println(result)
}


// 9. The land of no return
fun infiniteLoop(): Nothing{
    // never returns anything
    while(true){
        // runs forever
    }
}
