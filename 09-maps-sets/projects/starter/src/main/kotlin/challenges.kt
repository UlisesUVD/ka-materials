fun main(){


    // 1. valid statements

    // val map1 : Map<Int to Int> = emptyMap() // invalid 'to' in generic arguments
    // val map2 = emptyMap() // no data to infer types
    val map3 : Map<Int, Int> = emptyMap()

    val map4 = mapOf("One" to 1, "Two" to 2, "Three" to 3)

    println(map4[1])
    println(map4["One"])
    // map4["Zero"] = 0  // Map is read only, need mutable map to assign pairs
    // map4[0] = "Zero" // Map is read only, need mutable map to assign pairs


    val map5 = mutableMapOf(
        "NY" to "New York",
        "CA" to "California"
    )

    map5["NY"]
    map5["WA"] = "Washington"
    // map5["CA"] = null // cannot assign null to not nullable type in pair


    // 2. Unnecessary exercise

    // 3.
    println(mergeMaps(mapOf("a" to "A", "b" to "B"), mapOf("c" to "C", "b" to "V")))

    // 4.
    val testText = "This is just a test"
    println(occurrenceOfCharacters(testText))

    // 5.
    println(
        isInvertible( mapOf(
            "x" to 1,
            "y" to 2,
            "z" to 2
        ))
    )

    // 6. Name-Title look up

    val nameTitleLookup: Map<String, String?> = mutableMapOf(
        "Mary" to "Engineer",
        "Patrick" to "Intern",
        "Ray" to "Hacker"
    )

    println(nameTitleLookup)

    //nameTitleLookup["Patrick"] = null
    //nameTitleLookup.remove("Ray")

    // won't work because is cast to type Map which should be MutableMap
}

// 3. Merge maps

fun mergeMaps(map1: Map<String,String>, map2: Map<String, String>) : Map<String, String>{

    val mergedMap = mutableMapOf<String, String>()

    for ((key, value) in map1){
        mergedMap[key] = value
    }
    for ((key, value) in map2){
        mergedMap[key] = value
    }

    return mergedMap
}

// 4. Occurrence of characters

fun occurrenceOfCharacters(text: String): Map<Char, Int>{

    val occurrences = mutableMapOf<Char, Int>()
    for (char in text){
        if(char in occurrences){

            occurrences.get(char)?.let { occurrences.put(char, it +1) }
        } else {
            occurrences[char] = 1
        }
    }
    return occurrences
}

// 5. Is Invertible

fun isInvertible(map: Map<String, Int>): Boolean {

    val size = map.size
    val uniques = mutableSetOf<Int>()
    for((key, value) in map){
        uniques.add(value)
    }

    return uniques.size == size
}

