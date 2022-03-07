import com.sun.org.apache.xpath.internal.operations.Bool

fun main(){

    // 1. Run a lambda n times
    repeatTask(10) { println("Kotlin Apprentice is a great book") }

    // 2. Repeat sums

    lateinit var squareNumbers: (Int) -> Int

    squareNumbers= { a:Int -> Int
        if(a == 1){
            1
        } else {
            a * a + squareNumbers(a - 1)
        }
    }

    lateinit var fib: (Int) -> Int

    fib = { a:Int -> Int
        if(a <= 2){
             a
        } else {
            fib(a-1) + fib(a-2)
        }
    }

    println(mathSum(10, squareNumbers))
    println(mathSum(10, fib))


    // 3.- Functional ratings

    val appRatings = mapOf(
        "Calendar Pro" to arrayOf(1,5,5,4,2,1,5,4),
        "The Messenger" to arrayOf(5,4,2,5,4,1,1,2),
        "Socialise" to arrayOf(2,1,2,2,1,2,4,2)
    )

    val averageRatings = mutableMapOf<String, Double>()

    for((name, ratings) in appRatings){
        val sum = ratings.reduce { acc, i -> acc + i  }
        val average = sum.toDouble() / ratings.size
        averageRatings[name] = average
    }

    val goodApps = averageRatings.filter { it.value > 3.0 }.map { it.key }
    println(goodApps)
}

fun repeatTask(times: Int, lambda: () -> Unit){
    repeat(times) {
        lambda()
    }
}

fun mathSum(length: Int, series: (Int) -> Int): Int{
    return series(length)
}