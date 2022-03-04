import java.util.Random

fun main(){


    // 1. Valid statements
    // val array1 = Array<Int>() // no size for array
    // val array2 = arrayOf() // cannot infer type without elements
    val array3 : Array<String> = arrayOf()

    // 2.
    val array4 = arrayOf(1,2,3)
    println(array4[0])
    // println(array4[5]) // error, index out of range
    array4[0] = 4

    val array5 = arrayOf(1,2,3)
    array5[0] = array5[1]
    // array5[0] = "Six" // Error, trying to add element of different type
    // array5 += 6 // cannot be reassigned cause is val, if the array were var, it could be recreated and reassigned

    // for item in array5 { println(item) } // for loops cannot be written as single liners


    // 3.
    val list1 = listOf(1,2,3,3,4,5)
    println(removeOne(3, list1).joinToString(", "))

    // 4.
    val list2 = listOf(1,2,3,3,4,5)
    println(remove(3, list2).joinToString(", "))

    // 5.
    val toReverse = arrayOf(1,2,3,4,5)
    println(reverse(toReverse).joinToString(", "))

    // 6.
    val toRandomize = arrayOf(1,2,3,4,5)
    println(randomized(toRandomize).joinToString(", "))


    val toMin = arrayOf(1,2,3,4,5)
    println(min(randomized(toMin)))

    val toMax = arrayOf(1,2,3,4,5)
    println(max(randomized(toMax)))

    val toMinMax = arrayOf(1,2,3,4,5)
    println(minMax(randomized(toMinMax)))
}


// 3. Remove first occurrence of element

fun removeOne(item: Int, list: List<Int>): List<Int>{

    val mutableList = list.toMutableList()
    val index = mutableList.indexOf(item)
    mutableList.removeAt(index)
    return mutableList.toList()

}

// 4. Remove all occurrences of element
fun remove(item: Int, list: List<Int>): List<Int>{

    val mutableList = list.toMutableList()

    while (item in mutableList){
        mutableList.remove(item)
    }
    return mutableList.toList()

}

// 5. Reverse array
fun reverse(array: Array<Int>): Array<Int>{
    val size = array.size
    val reversed = Array(size) { 0 }

    for(i in (size -1) downTo 0){
        reversed[reversed.size-1 - i ] = array[i]
    }

    return reversed
}

// 6.
val random = Random()
fun rand(from: Int, to: Int): Int{
    return random.nextInt(to-from) + from
}

fun randomized(array: Array<Int>): Array<Int>{
    val size = array.size
    var randomized = Array(size){0}

    for(i in 0 until size){

        var randomIndex = rand(0, size)
        while (randomized[randomIndex] != 0){
            randomIndex = rand(0, size)
        }
        randomized[randomIndex] = array[i]
    }
    return randomized
}

fun min(array: Array<Int>): Int?{
    if(array.isEmpty()) return  null
    if(array.size == 1) return  array[0]
    var min = array[0]
    for (i in 1 until array.size){
        if(array[i] < min){
            min = array[i]
        }
    }
    return min
}

fun max(array: Array<Int>): Int?{
    if(array.isEmpty()) return  null
    if(array.size == 1) return  array[0]
    var max = array[0]
    for (i in 1 until array.size){
        if(array[i] > max){
            max = array[i]
        }
    }
    return max
}

fun minMax(array: Array<Int>): Pair<Int?, Int?>{
    if(array.isEmpty()) return  Pair(null,null)
    if(array.size == 1) return  Pair(array[0],array[0])
    var max = array[0]
    var min = array[0]
    for (i in 1 until array.size){
        if(array[i] > max){
            max = array[i]
        }
        if(array[i] < min){
            min = array[i]
        }
    }

    return Pair(min, max)
}