fun main(){

    // 2. named arguments
    printFullName(firstName = "Ulises", lastName = "Verduzco")

    // 3. calculate full name
    val fullName = calculateFullName(firstName = "Ulises", lastName = "Verduzco")
    println(fullName)

    // 4. calculate full name with length
    val fullNameWithLength = calculateFullNameWithLength(firstName = "Ulises", lastName = "Verduzco")
}

// 1. print full name
fun printFullName(firstName: String, lastName: String){
    println("$firstName $lastName")
}

// 3. calculate full name
fun calculateFullName(firstName: String, lastName: String): String{
    return "$firstName $lastName"
}

// 4. Full name with length
fun calculateFullNameWithLength(firstName: String, lastName: String): Pair<String, Int>{
    val fullName = "$firstName $lastName"
    return Pair( fullName, fullName.length)
}