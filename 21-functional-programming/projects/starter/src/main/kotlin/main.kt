import java.util.*

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

    // 1. Getting started

    val firstRobot = Robot("Experimental Space Navigation Droid")
    val secondRobot = Robot("Extra-Terrestrial Air Safety Drone")


    Battlefield.beginBattle(firstRobot, secondRobot, ::onBattleEnded)

    // 2. First class and higher-order functions

    // First class means that functions are treated as any other type
    // You can assign a function to a variable, pass them as arguments, receive them as results, etc..

    // If a function takes another function as a parameter or returns a function
    // It is a higher-order function

    // 3. Function types
    // To declare that some variable is a function you need to declare the type
    // as you declare :Int, :Boolean, :MutableArrayList<T> , etc..
    // But given that functions can receive and return more types there is a special way to declare them
    // arguments in a parenthesis, followed by an '->' arrow operator, followed by a return type
    // ( arguments ) -> returnType

    // myIntToDoubleFunction: (Int) -> Double
    // myConcatenationFunction: (String, String, Int) -> String
    // myEmptyUnitFunction : () -> Unit

    // 4. Passing a function as an argument
    // Battlefield.kt

    // 5. Returning functions

    val randomFunction = someFunction()
    println(randomFunction())

    // 6. Lambdas
    // Lambdas are a way to define inline functions
    // They are special because whatever it is the las expression
    // The returned value is the result of that expression and the return type of the declaration can be omitted
    // because it can be inferred from the last expression
    // you can see a '^lambda' indicator provided by the ide to point out the last expression

    val pow = { base: Int, exponent: Int ->
        Double
        println("This does not return nothing")
        Math.pow(base.toDouble(), exponent.toDouble())
    }

    println(pow(2, 4))

    // this is the same but with explicit type declaration
    // here you can omit the types in the lambda arguments and return
    // because they are declared explicitly in the val definition
    val anotherPow: (Int, Int) -> Double = { base, exponent ->
        Math.pow(base.toDouble(), exponent.toDouble())
    }

    println(anotherPow(2, 4))


    val onBattleEnded: (Robot) -> Unit = { winner: Robot ->
        Unit
        winner.report("Win!")
    }

    Battlefield.beginBattle(firstRobot, secondRobot, onBattleEnded)

    // 7. How does lambdas work
    // Lambdas gets compiled into functions that complies with the 'interface FunctionN<in P1, out R>'


    // 8. Closures
    // lambdas can access variables outside of its scope


    var result = 0

    val sum = { a: Int, b: Int ->
        Int
        result = a + b
    }

    sum(5, 18)

    // 9. Extension functions
    // extension functions receive as first parameter the variable that is calling them
    fun String.print() = System.out.println(this)
    val string = "Hello world"
    string.print()

    // see extensions.kt

    // 10. Lambdas with receivers
    // just as normal functions you can attach lambdas to receivers

    // 11. Anonymous functions
    // you can assign a function to a var or pass the expression directly as an argument
    // so you don't need to assign a name to such function

    // assign function to a constant
    val reportOnWin = fun(robot: Robot) { robot.report("Win!") }
    Battlefield.beginBattle(firstRobot, secondRobot, reportOnWin)

    // pass the whole block as an argument
    Battlefield.beginBattle(firstRobot, secondRobot) {
        this.report("Win!")
    }


    // 12. Returning from lambdas
    // using 'return' inside a lambda will take you to the outer function
    // if you want to return to a certain point inside the lambda
    // you need to use qualified returns '@someTag'


    /*
    // this will never reach the println statement
    fun calculateEvent(){
        var result = 0
        (0..20).forEach{
            if(it % 3 == 0) return
            if(it % 2 == 0) result += it
        }

        println(result)
    }
    */

    // this will return to the next iteration like a 'continue'
    /*
    fun calculateEvent(){
        var result = 0
        (0..20).forEach{
            if(it % 3 == 0) return@forEach
            if(it % 2 == 0) result += it
        }

        println(result)
    }
     */

    // this will also return to outer function 'foreach' as a continue because of the nested function
    fun calculateEvent() {
        var result = 0
        (0..20).forEach(fun(value) {
            if (value % 3 == 0) return
            if (value % 2 == 0) result += value
        }
        )

        println(result)
    }


    calculateEvent()

    // 13. Inline functions
    // this will avoid the compilation into a FunctionN and pass the body of the function instead of the call
    // add 'inline' to the beginBattle() function and check the decompiled bytecode

    // 14. noinline
    // you can add noinline to avoid nested lambda functions to avoid being inlined if the outer function is inlined
    // add 'noinline' to the onBattleEnded parameter and check the decompiled bytecode

    // 15. crossinline
    // 'crossinline' is used to mark a lambda that should not allow a non-local 'return'

    // 16. Tail recursive function
    // You can use the 'tailrec' keyword to transform a tail recursive function
    // (a functions which last expression is a call to itself)
    // into an appropriate 'while' loop
    // modify the 'battle' function, so it becomes an appropriate while loop

    // 17. Collections standard library
    // you already worked with higher order functions and lambdas using Collections<T>, List<T>, etc..
    // some examples are filter, first, map, etc..

    val participants = arrayListOf<Robot>(
        Robot("Extra-Terrestrial Neutralization Bot"),
        Robot("Generic Evasion Drone"),
        Robot("Self-Reliant War Management Device"),
        Robot("Advanced Nullification Android"),
        Robot("Rational Network Defense Droid"),
        Robot("Motorized Shepherd Cyborg"),
        Robot("Reactive Algorithm Entity"),
        Robot("Ultimate Safety Guard Golem"),
        Robot("Nuclear Processor Machine"),
        Robot("Preliminary Space Navigation Machine"),
    )

    var topCategory = participants.filter { it.strength > 80 }

    println(topCategory)

    topCategory = participants.filter { it.strength > 80 }.take(3).sortedBy { it.name }

    println(topCategory)

    // 18. Infix notation
    // if a function is a member/extension function and receives only 1 argument
    // you can mark it as an infix function and then invoke it without dot or parenthesis
    // transform Robot.attack(robot:Robot) into an infix function

    // 19. Sequences
    // Sequences are lazy evaluated collections for very large datasets
    // they take a lambda as a generator argument

    val random = Random()

    val sequence = generateSequence {
        random.nextInt(100)
    }

    sequence.take(15).sorted().forEach { println(it) }

}


fun onBattleEnded(robot: Robot){
    robot.report("Win!")
}


// 5.

fun someFunction() : () -> Int {
    return ::anotherFunction
}

fun anotherFunction() : Int{
    return Random().nextInt()
}

// 15.
inline fun otherFunction( crossinline body: () -> Unit){
    yetAnotherFunction{
        body()
    }
}
fun yetAnotherFunction( body: () -> Unit){

}