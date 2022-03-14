fun main(){

    // 1. Methods extensions
    val monthsUntilSummer = SimpleDate4("September").monthsUntilSummerBreak()
    println("Months until summer break: $monthsUntilSummer")

    // 2. Companion extensions

    val primeFactors = MyMath.primeFactors(81)
    println(primeFactors)
}

// 1.
fun SimpleDate4.monthsUntilSummerBreak(): Int{
    val monthIndex = months.indexOf(month)
    return if(monthIndex in 0..months.indexOf("June")){
        months.indexOf("June") - months.indexOf(month)
    } else if(monthIndex in months.indexOf("June") .. months.indexOf("August")){
        0
    } else {
        months.indexOf("June") + (12 - months.indexOf(month))
    }
}

// 2.

fun MyMath.Companion.primeFactors(value: Int): List<Int>{
    var remainingValue = value

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

