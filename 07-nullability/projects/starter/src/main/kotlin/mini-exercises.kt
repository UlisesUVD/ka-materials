fun main(){

    // 1.
    var myFavoriteSong : String?
    myFavoriteSong = null

    // 2.
    var parsedInt = "10".toIntOrNull()
    // return null if the string is not a valid representation of a number
    // so the type is inferred as Int?
    println("parsedInt = $parsedInt")

    // 3.
    parsedInt = "Dog".toIntOrNull()
    println("parsedInt = $parsedInt")

    // 1. Smart casting

    var noNullableFavoriteSong : String
    var nullableFavoriteSong: String?

    if(myFavoriteSong != null){
        noNullableFavoriteSong = myFavoriteSong
        println("my favorite song is $noNullableFavoriteSong")
    } else {
        nullableFavoriteSong = myFavoriteSong
        println("I don't have a favorite song")
    }

    // 2.
    myFavoriteSong = "I'll take everything"
    if(myFavoriteSong != null){
        noNullableFavoriteSong = myFavoriteSong
        println("my favorite song is \"$noNullableFavoriteSong\"")
    } else {
        nullableFavoriteSong = myFavoriteSong
        println("I don't have a favorite song")
    }
}