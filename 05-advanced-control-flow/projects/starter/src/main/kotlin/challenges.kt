import kotlin.random.Random

fun main (){


    // 1

    var sum = 0
    for(i in 0..5){
        sum += i
    }

    // 6 iterations, 0,1,2,3,4,5
    // sum = 15
    println("sum = $sum")

    // 2

    var aLotOfAs = ""
    while (aLotOfAs.length < 10){
        aLotOfAs += "a"
    }

    // 9 'a's at the end of the cicle
    println("aLotOfAs length = ${aLotOfAs.length}")


    // 3
    val (x, y, z) = Triple(Random.nextInt(5),Random.nextInt(5),Random.nextInt(5))
    when{
        x == y && y == z -> println("x = y = z")
        z == 0 -> println("on the x/y plane")
        y == 0 -> println("on the x/z plane")
        x == 0 -> println("on the y/z plane")
        else -> {
            println("nothing special")
        }
    }
    // This one has an error, because it doesn't consider cases whit 2 vars in 0
    // and will return the message corresponding to a plane even when it wont be true

    // 4
    // A closed range cannot be empty because its inclusive so it will take at leas the boundaries
    val range = 0..0
    println("first = ${range.first}, last = ${range.last}")

    //5

    println("Commencing countdown")
    for( i in 10 downTo 0){
        println("$i")
    }

    // 6
    println("Commencing countdown")
    for( i in 0 .. 10 ){
        println("${i.toDouble()/10}")
    }

}