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

    // 1. Lambda basics

    var multiplyLambda: (Int, Int) -> Int


    multiplyLambda = {a: Int, b: Int -> Int
        a*b
    }

    val lambdaResult = multiplyLambda(2,4)
    println(lambdaResult)

    // 2. Shorthand Syntax
    multiplyLambda = { a, b ->
        a*b
    }

    // 3. 'it' keyword
    var doubleLambda = { a: Int ->
        2*a
    }

    doubleLambda = { 2 * it }

    val square: (Int) -> Int = { it * it }


    // 4. Lambdas as arguments

    val addLambda: (Int, Int) -> Int = { a:Int, b:Int -> Int
        a+b
    }

    // calling defined lambda
    println(
        operateOnNumbers(2, 4, addLambda)
    )

    // defining lambda as argument
    println(
        operateOnNumbers(2, 4, { a: Int, b: Int -> a+b })
    )

    // defining lambda as argument with inferred types
    println(
        operateOnNumbers(2, 4, { a, b -> a+b })
    )

    // defining lambda as last external argument
    println(
        operateOnNumbers(2, 4) { a, b -> a + b }
    )

    // using a lambda from the standar libraries
    println(
        operateOnNumbers(2, 4, Int::plus)
    )

    // 5. Lambdas with no meaningful return type

    val unitLambda : () -> Unit = {
        println("No returned value lambda")
    }
    unitLambda()

    // 6. Capturing from the enclosing scope

    var counter = 0

    val incrementCounter: () -> Unit = { counter += 1 }

    incrementCounter()
    incrementCounter()
    incrementCounter()
    incrementCounter()
    incrementCounter()

    println(counter)

    val counter1 = countingLambda()
    val counter2 = countingLambda()

    println(counter1())
    println(counter2())
    println(counter1())
    println(counter1())
    println(counter2())

    // 7. Custom sorting with lambdas

    val names = arrayOf("ZZZZZZ", "BB", "A", "CCCC", "EEEEE")
    println(names.sorted())

    val namesByLength = names.sortedWith(compareBy { s: String ->
        -s.length
    })

    println(namesByLength)

    // 8. Iterating over colections with lambdas
    val values = listOf(1,2,3,4,5,6)

    values.forEach {
        println("${it * it}")
    }

    val prices = listOf(1.5, 10.0, 4.99, 2.30, 8.19)

    println(prices.filter(::filteringFunction))
    println(prices.filter { it > 5.0 })

    val salePrices = prices.map { it * 0.9 }
    println(salePrices)
    val salePricesText = prices.map { (it * 0.9).toString() }
    println(salePricesText)

    println(prices)
    var sum = prices.fold(0.0) { a: Double, b: Double ->
        println("a=$a, b=$b")
        a + b
    }

    println(sum)

    println(prices)
    sum = prices.reduce { a: Double, b: Double ->
        println("a=$a, b=$b")
        a + b
    }
    // similar to fold, but doesn't require an initial value, the first element is taken as initial

    println(sum)


    val stock = mapOf(
        1.5 to 5,
        10.0 to 2,
        4.99 to 20,
        2.30 to 5,
        8.19 to 30
    )

    var stockSum = 0.0
    stock.forEach {
        stockSum += it.key * it.value
    }
    println(stockSum)

}

// 4.
fun operateOnNumbers(
    a: Int,
    b: Int,
    operation : (Int, Int) -> Int
): Int{
    val result = operation(a,b)
    println(result)
    return result
}

// 6.
fun countingLambda() : () -> Int {
    var counter = 0
    val incrementCounter = {
        counter += 1
        counter
    }
    return incrementCounter
}

fun filteringFunction(value: Double): Boolean{
    return value > 5.0
}