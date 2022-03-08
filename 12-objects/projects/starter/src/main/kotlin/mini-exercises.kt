fun main() {

    // 1
    val marie = StudentExercise.new("Marie", "Curie")
    val albert = StudentExercise.new( "Albert", "Einstein")
    val emmy = StudentExercise.new( "Emmy", "Noether")

    println(StudentExercise.getNumberOfStudents())
}

// 1.
data class StudentExercise(
    val id: Int,
    val firstName: String,
    val lastName: String
){
    init {
        students += 1
    }
    
    val fullName = "$firstName $lastName"

    companion object{
        var students = 0
        var currentId = 0

        fun getNumberOfStudents(): Int { return students }

        fun new(firstName: String, lastName: String):StudentExercise{
            currentId += 1
            return StudentExercise(currentId, firstName, lastName)
        }
    }
}