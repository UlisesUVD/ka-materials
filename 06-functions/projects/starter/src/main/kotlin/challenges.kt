fun main(){

    // 1, It's prime
    println(isPrime(6))
    println(isPrime(13))
    println(isPrime(8893))

    // 2. Recursive functions
    println(fibonacci(7))
}


// 1. It's prime
fun isNumberDivisible(number: Int, divisor: Int): Boolean{
    return number % divisor == 0
}

fun isPrime(number: Int): Boolean {
    for (i in 2 until number){
        if(isNumberDivisible(number, i)){
            return false
        }
    }
    return true
}

// 2. Recursive functions
fun fibonacci(number: Int): Int{
    if(number > 2) {
        return fibonacci(number -1) + fibonacci(number - 2)
    } else {
        return 1
    }
}