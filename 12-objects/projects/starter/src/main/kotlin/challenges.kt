fun main(){


    // 1. Threshold checker

    println(Threshold.isAboveThreshold(2))
    println(Threshold.isAboveThreshold(12))

    // 2. Load Student Factory

    val student1 = StudentV3.loadStudent(mapOf("first_name" to "John", "last_name" to "Doe"))
    val student2 = StudentV3.loadStudent(mapOf("firstName" to "Jane", "lastName" to "Doe"))

    println(student1)
    println(student2)

    // 3. Anonymous Object

    val checker = object : ThresholdChecker{
        override val lower: Int
            get() = 10
        override val upper: Int
            get() = 20

        override fun isLit(value: Int): Boolean {
            return value > upper
        }

        override fun tooQuiet(value: Int): Boolean {
            return value < lower
        }

    }

    println(checker.isLit(25))
    println(checker.isLit(12))
    println(checker.tooQuiet(9))
    println(checker.tooQuiet(12))



}

object Threshold{

    var threshold = 10
    fun isAboveThreshold(a:Int) : Boolean{
        return a > threshold
    }

}

data class StudentV3(
    var id:Int,
    var firstName: String,
    var lastName: String
){

    companion object Factory{

        var currentId = 0

        fun loadStudent(studentMap: Map<String, String>): StudentV3{
            currentId += 1

            val firstName =  studentMap["first_name"] ?: "First"
            val lastName = studentMap["last_name"] ?: "Last"

            return StudentV3(currentId, firstName, lastName)

        }

    }

}

// 3.

interface ThresholdChecker{
    val lower: Int
    val upper: Int

    /**
     * Returns true if value is higher than the upper threshold and false otherwise
     */
    fun isLit(value: Int): Boolean
    /**
     * Returns true if value is less than the lower threshold and false otherwise
     */
    fun tooQuiet(value: Int): Boolean
}