fun main (){


    val bobData = mutableMapOf(
        "name" to "Bob",
        "profession" to "CardPlayer",
        "country" to "USA"
    )

    bobData.put("state", "CA")
    bobData["city"] = "San Francisco"

    var oldName = bobData.put("name", "Bobby")
    bobData["profession"] = "Mailman"

    val pair = "nickname" to "Bobby D"
    bobData += pair

    println(bobData)
}