val movieLists = mutableMapOf<String, MovieList>()

fun main(){


    val jane = User(1, "Jane", "Appleseed")
    val john = User(2, "John", "Appleseed")

    jane.addList(MovieList("Terror", mutableListOf("IT", "The Conjuring")))
    john.addList(MovieList("Action", mutableListOf("John Wick", "Mission Impossible")))

    jane.addList(MovieList("Action", mutableListOf("John Wick", "Mission Impossible", "Die Hard")))

    john.list("Action")?.print()
}



data class User(var id:Int, var firstName: String, var lastName: String){

    fun addList(movieList : MovieList){
        movieLists[movieList.name] = movieList
    }

    fun list(name: String): MovieList?{
        return if(movieLists.contains(name)){
            movieLists[name]
        } else {
            null
        }
    }

}

data class MovieList(var name: String, var list: MutableList<String>){

    fun print(){
        println(list.joinToString(", "))
    }
}