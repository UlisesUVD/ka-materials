import kotlin.random.Random

fun main(){


    // Ex 1

    val range = 1..10
    for(i in range){
        println("$i^2 = ${Math.pow(i.toDouble(), 2.0)}")
    }

    // Ex 2

    for(i in range){
        println("sqrt($i) = ${Math.sqrt(i.toDouble())}")
    }

    // Ex 3

    var sum = 0
    for (row in 1 until 8 step 2){
        println("row = $row")
        for (column in 0 until 8){
            sum += column * row
        }
    }
    println("sum of odd rows = $sum")

    // Ex 1

    val age = Random.nextInt(100)

    var stageOfLife = when(age){
        in 0..2 -> "Infant"
        in 3 .. 12 -> "Child"
        in 13 .. 19 -> "Teenager"
        in 20 .. 39 -> "Adult"
        in 40 .. 61 -> "Elder"
        in 62 .. 120 -> "Ancient"
        else -> "Are you sure about that?"
    }

    println("$age years means.. $stageOfLife ")


    // Ex 2
    val name = listOf("Ringo", "John", "Paul", "The other guy" ).random()

    val pair = Pair(age, name)

    val message = when(pair.first){
        in 0..2 -> "${pair.second} is an infant"
        in 3 .. 12 -> "${pair.second} is a child"
        in 13 .. 19 -> "${pair.second} is a teenager"
        in 20 .. 39 -> "${pair.second} is an adult"
        in 40 .. 61 -> "${pair.second} is an elder"
        in 62 .. 120 -> "${pair.second} is ncient"
        else -> "Are you sure about that?"
    }
    println(message)
}