import kotlin.math.log2
import kotlin.random.Random

fun main(){
    // Challenges

    // Ex 1
    val firstName = "Joe"
    if(firstName == "Howard"){
        val lastName = "Lucas"
    } else  if(firstName == "Ray"){
        val lastName = "Wenderlich"
    }

    // val fullName = firstName + " " + lastName
    // lastName is scoped inside if and else

    // Ex 2
    val answer1 = true && false // false
    val answer2 = false || false // false
    val answer3 = (true && 1 != 2) || (4 > 3 && 100 < 1) // true
    val answer4 = ((10 /2) >3) && ((10 % 2) == 0) // true

    // Ex 3
    // current and next position in a chessboard
    val positionInChessboard = Random.nextInt(64)

    println(positionInChessboard)
    var row = positionInChessboard / 8
    println(row)
    var col = positionInChessboard - (row*8)
    println(col)
    println("square $positionInChessboard is in row $row and column $col")

    // next position
    if(positionInChessboard < 63){
        col += 1
        if(col > 7){
            col = 0
            row += 1
        }
        println("next position would be row $row and column $col")
    } else {
        println("out of board")
    }

    // Ex 4

    //Quadratic equation = ax^2 + bx + c
    run{
        val a = Random.nextInt(10)
        val b = Random.nextInt(20)
        val c = Random.nextInt(10)

        println("${a}x^2 + ${b}x + $c = 0")

        val solution1 = (b*-1 + Math.sqrt( (Math.pow(b.toDouble(), 2.0) - 4.0*(a*c)) )  )/2*a
        val solution2 = (b*-1 - Math.sqrt( Math.pow(b.toDouble(), 2.0) - 4.0*(a*c) )  )/2.0*a
        println( Math.pow(b.toDouble(), 2.0) - 4.0*(a*c))
        println("root 1 $solution1")
        println("root 2 $solution2")
    }

    // Ex 5
    // find days in february for any year
    var month = listOf("january", "february", "march", "april",
        "may", "june", "july", "august",
        "september", "october", "november", "december").random()

    var year = Random.nextInt(2023)
    val days = if(month in listOf("january", "march", "may", "july", "august", "october", "december") ){
        31
    } else if(month == "february"){
        if( (year%4 == 0 && year %100 != 0) || (year%4 == 0) && year %400 == 0){
            29
        } else{
            28
        }
    } else {
        30
    }

    println("The date is $month of $year, this month has $days days")

    // Ex 6
    // is power of 2?
    val number = listOf<Double>(2.0, 123.0, 4.0, 42.0, 8.0, 3.1416).random()
    val result = log2(number)
    if(result.rem(1)  == 0.0){
        println("$number is a power of 2")
    } else {
        println("$number is not a power of 2")
    }

    // Ex 7
    // first n powers of 2

    val limit = Random.nextInt(10)
    var counter = 1.0
    println("n = $limit")
    while (counter <= limit){
        println("2^$counter = ${Math.pow(2.0, counter)}")
        counter += 1.0
    }

    // Ex 8
    // fibonacci

    run{
        val limit = Random.nextInt(10)
        var a = 0
        var b = 1
        var counter = 0
        println("fibonacci = $limit")
        while (counter < limit){
            println("${b}")
            val t = b
            b += a
            a = t
            counter += 1
        }

    }

    //Ex 9
    // factorial of number
    run{
        val number = Random.nextInt(10)
        var counter = 1
        var factorial = 1
        if(number == 0){
            println("factorial of $number is $number")
            return
        }
        while (counter <= number){
            factorial *=counter
            counter += 1
        }
        println("factorial of $number is $factorial")
    }

    //Ex 10
    // Probability of number with dice

    run{
        var number = Random.nextInt(2, 12)
        var chances = 0
        for (dice1 in 1..6){
            for (dice2 in 1..6){
                if(dice1 + dice2 == number){
                    chances += 1
                    if(dice1 == dice2){
                        chances += 1
                    }
                }
            }
        }

        println("${chances/2} ways to get $number from 21  = ${chances.toDouble()/2/21.0 * 100}%")
    }
}