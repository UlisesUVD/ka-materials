fun main(){


    // 1.

    var name: String? = "Ray"
    // var age: Int = null // invalid, no nullable type
    // var distance: Float = 26.7 // invalid, missing F
    var middleName: String? = null

    // 2. is whole division?
    var times = divideIfWhole(10, 3)

    if(times != null){
        println("Yep, is divisible $times times")
    } else {
        println("Not divisible :(")
    }

    // 3. refactor message with elvis
    val result = times ?: 0
    var message: String =  "It divides $result times"
    println(message)

}

fun divideIfWhole(num: Int, divisor: Int): Int?{
    var numTemp = num
    if(numTemp % divisor != 0){
        return null
    } else {
        var counter = 0
        while (numTemp > 0){
            numTemp -= divisor
            counter += 1
        }
        return counter
    }
}