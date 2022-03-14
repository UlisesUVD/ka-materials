import kotlin.math.PI

fun main() {


    // 1.
    val circle = Circle(10.0)
    println("radius = ${circle.radius} area = ${circle.area}")

    circle.grow(3)
    println("radius = ${circle.radius} area = ${circle.area}")

    // 2.
    var simpleDate = SimpleDate("February", 27)
    println("Initial day : ${simpleDate.month} ${simpleDate.day}")
    simpleDate.advance()
    println("Next day : ${simpleDate.month} ${simpleDate.day}")
    simpleDate.advance()
    println("Next day : ${simpleDate.month} ${simpleDate.day}")


    simpleDate = SimpleDate("July", 26)
    println("Initial day : ${simpleDate.month} ${simpleDate.day}")
    simpleDate.advance()
    println("Next day : ${simpleDate.month} ${simpleDate.day}")
    simpleDate.advance()
    println("Next day : ${simpleDate.month} ${simpleDate.day}")

    simpleDate = SimpleDate("December", 30)
    println("Initial day : ${simpleDate.month} ${simpleDate.day}")
    simpleDate.advance()
    println("Next day : ${simpleDate.month} ${simpleDate.day}")
    simpleDate.advance()
    println("Next day : ${simpleDate.month} ${simpleDate.day}")

    println(81.primeFactors())
}

// 1.
class Circle(var radius: Double = 0.0){
    val area: Double
        get(){
            return PI * radius * radius
        }

    fun grow(factor: Int){
        radius *= factor
    }
}

// 2.
class SimpleDate(var month: String, var day: Int){
    fun advance(){
        val nextDay = day + 1
        if(nextDay > limit){
            val nextMonth = months.indexOf(month) + 1
            month = if(nextMonth > months.size - 1){
                months[0]
            } else {
                months[nextMonth]
            }
            day = 1
        } else {
            day += 1
        }
    }

    private val limit : Int
        get(){
            return when(month){
                in listOf("January", "March", "May", "July", "August", "October", "December") ->{
                    31
                }
                in listOf( "April", "June",  "September", "November") -> {
                    30
                }
                "February" -> {
                    28
                }
                else -> {
                    0
                }
            }
        }

}

// 3.

fun MyMath.Companion.isOdd(number: Int): Boolean{
    return (number == 1) || number % 2 != 0
}

fun MyMath.Companion.isEven(number: Int): Boolean{
    return number % 2 == 0
}

// fun Int.primeFactors() = MyMath.Companion.primeFactors(this)
// not sure if this is adequate

fun Int.primeFactors() : List<Int>{
    var remainingValue = this

    var testFactor = 2
    val primes = mutableListOf<Int>()

    while (testFactor * testFactor <= remainingValue ){
        if(remainingValue % testFactor == 0){
            primes.add(testFactor)
            remainingValue /= testFactor
        } else{
            testFactor += 1
        }
    }

    if(remainingValue > 1){
        primes.add(remainingValue)
    }

    return primes
}
