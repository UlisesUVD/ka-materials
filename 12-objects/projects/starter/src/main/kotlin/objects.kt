/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

fun main() {

    // 1. Singletons: Singleton is a pattern to restrict a class to have only one instance while the app is running

    // 2. Named objects. In kotlin 'object' is used to define a custom type ("class") that
    // can only have one instance, this avoids al the boilerplate code for the Singleton implementation

    // 3. Getting started. Examine the X object and its bytecode

    // 4. Singleton use cases

    val marie = Student(1, "Marie", "Curie")
    val albert = Student(2, "Albert", "Einstein")
    val emmy = Student(3, "Emmy", "Noether")

    StudentRegistry.addStudent(marie)
    StudentRegistry.addStudent(albert)
    StudentRegistry.addStudent(emmy)

    StudentRegistry.listAllStudents()

    // 5. Using static members. Static members (functions or properties) are used in Java and Swift to
    // provide functionality that is the same for all instances of the class, also to provide functionality
    // without creating an instance of the class

    // 6. Creating companion objects. Kotlin does not have 'static' members, it has 'companion's


    val emmy2 = Scientist.newScientist("Emmy", "Noether")
    val isaac = Scientist.newScientist("Isaac", "Newton")
    val nick = Scientist.newScientist("Nikola", "Tesla")

    ScientistRepository.addScientist(emmy2)
    ScientistRepository.addScientist(isaac)
    ScientistRepository.addScientist(nick)

    ScientistRepository.listAllScientist()

    // 7. Companion naming and accessing from Java

    // val test = Scientist.Companion.newScientist("Justa", "Testo") // default
    val test = Scientist.Factory.newScientist("Justa", "Testo") // named

    // 8. Using anonymous objects. They are used to define implementations without defining a concrete class
    // just like the listeners and callback for some UI components like button.setOnclickListener( anonymousObject)

    val counter = object : Counts{
        override fun studentCount(): Int {
            return StudentRegistry.allStudents.size
        }

        override fun scientificCount(): Int {
            return ScientistRepository.allScientist.size
        }
    }
    
    println(counter.studentCount())
    println(counter.scientificCount())

}


// 4
data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String
){
    val fullName = "$firstName $lastName"
}

object StudentRegistry{

    val allStudents = mutableListOf<Student>()

    fun addStudent(student: Student){
        allStudents.add(student)
    }

    fun removeStudent(student: Student){
        allStudents.remove(student)
    }

    fun listAllStudents(){
        allStudents.forEach{
            println(it.fullName)
        }
    }
}

object JsonKeys{
    const val JSON_KEY_ID = "id"
    const val JSON_KEY_FIRST_NAME = "first_name"
    const val JSON_KEY_LAST_NAME = "last_name"

}

// 6 Companion
class Scientist private constructor(
    val id: Int,
    val firstName: String,
    val lastName: String
){
    companion object Factory{ // "Factory" to have a name reference to the companion instead of the default "Companion"
        var currentId = 0
        fun newScientist(firstName: String, lastName: String): Scientist{
            currentId += 1
            return Scientist(currentId, firstName, lastName)
        }
    }



    var fullName = "$firstName $lastName"
}

object ScientistRepository{
    val allScientist = mutableListOf<Scientist>()

    fun addScientist(scientist: Scientist){
        allScientist.add(scientist)
    }

    fun removeScientist(scientist: Scientist){
        allScientist.remove(scientist)
    }

    fun listAllScientist(){
        allScientist.forEach {
            println("${it.id} ${it.fullName}")
        }
    }
}

// 8.
interface Counts{
    fun studentCount(): Int
    fun scientificCount() : Int
}
