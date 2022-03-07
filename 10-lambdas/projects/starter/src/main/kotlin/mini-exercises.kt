fun main() {



    val nameList = listOf("Benson", "Finn", "Nick", "Amanda", "Munch", "Stabler")

    // Ex 1. Concatenate with fold
    val concatenated = nameList.fold("") { acc: String, current: String -> "$acc $current" }
    println(concatenated)


    // Ex 2. Filter and concatenate
    val filteredAndConcatenated =
        nameList.filter { it.length > 4 }.fold("") { acc: String, current: String -> "$acc $current" }
    println(filteredAndConcatenated)


    // Ex 3. filter minors from map
    val namesAndAges = mapOf(
        "Benson" to 40,
        "Finn" to 50,
        "Nick" to 35,
        "Amanda" to 35,
        "Munch" to 70,
        "Stabler" to 45
    )

    val minors = namesAndAges.filter { it.value < 18 }
    println(minors)

    // Ex 4 filter adults and return list
    val adults = namesAndAges.filter { it.value >= 18 }.map { it.key }
    println(adults)
}