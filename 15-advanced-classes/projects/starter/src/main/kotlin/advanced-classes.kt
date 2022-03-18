import java.awt.Image

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

    // 1. Introducing Inheritance
    val john = Person("Johnny", "Appleseed")
    val jane = Student("Jane", "Appleseed")

    println(john.fullName())
    println(jane.fullName())

    val history = Grade('B', 9.0, 3.0)
    jane.recordGrade(history)
    // john.recordGrade(history) // John is Person but not Student, he can't have grades

    // 2. Polymorphism, instances can be used as different types depending on the context
    // here, the instance of OboePlayer can be used as a Person argument because is a child class of it

    val oboePlayer = OboePlayer("Jane", "Appleseed")

    println(phonebookName(john))
    println(phonebookName(oboePlayer))

    // 3. Runtime hierarchy checks

    var hallMonitor = Student("Jill", "Bananapeel")

    hallMonitor = oboePlayer

    println(hallMonitor.minimumPracticeTime) // error

    println(hallMonitor is OboePlayer)
    println(hallMonitor !is OboePlayer)
    println(hallMonitor is Person)

    // println( (oboePlayer as Student).minimumPracticeTime) // no practice time in student

    println( (oboePlayer as? BandMember)?.minimumPracticeTime)

    println(afterClass(oboePlayer))
    println(afterClass(oboePlayer as Student))

    // 4. Inheritance methods and override

    val math = Grade('B', 9.0, 3.0)
    val science = Grade('F', 9.0, 3.0)
    val physics = Grade('F', 9.0, 3.0)
    val chemistry = Grade('F', 9.0, 3.0)

    val dom = StudentAthlete("Dom", "Grady")

    dom.recordGrade(math)
    dom.recordGrade(science)
    dom.recordGrade(physics)
    dom.recordGrade(chemistry)
    println("is eligible? ${dom.isEligible}")

    // 5. Introducing super
    // when using 'super' you call the nearest corresponding function upwards the hierarchy

    // 6. When to call supper

    // 7. Preventing Inheritance
    // Inheritance is prevented by default, you need to add 'open' to your classes and methods
    // to allow them to be extended and override

    // 8. Abstract classes
    // sometimes you want to have classes available for extension and implementation
    // but you don't want that class to be directly instantiated
    // 'abstract' classes are 'open' by default

    val human = Human("1/1/2000")
    //error not instantiable
    // val mammal = Mammal("1/1/2000")

    // 9. Sealed Classes
    // Useful when you want to be sure that an instance is part of a specific set of types
    // They are similar to 'enum classes'

    val circle1 = Shape.Circle(4)
    val circle2 = Shape.Circle(2)

    val square1 = Shape.Square(4)
    val square2 = Shape.Square(2)

    println(size(circle1))
    println(size(square1))

    // 10. Secondary Constructors
    // you can use the 'constructor' keyword to add more constructors to your class
    // in the default constructor you have been using 'constructor' is omitted

    // 11. Nested and inner classes
    // you can do this when classes are closely related

    val mazda = Car("Mazda")
    val mazdaEngine = mazda.Engine("Rotary")

    println(mazdaEngine.toString())

    // 12. Visibility modifiers

    // public: Visible from everywhere
    // private: visible only from inside the 'class' in classes and within the same file and top level functions
    // protected: visible only within subclasses for class inheritance
    // internal: visible only within the same module

    val privilegedUser = Privilege.PrivilegedUser("sashinka", "1234", 21)
    val privilege = Privilege(1, "invisibility")
    privilegedUser.addPrivilege(privilege)
    println(privilegedUser.about())

    // 13. When and why to subclass
    // it depends of the case

    // 14. Single responsibility
    // Often you want subclass because it allows you to set functions and attributes only when they are required
    // You don't want to add a bunch of attributes in a type that might never be used


    // 15. Strong types
    // Using more specific types down a hierarchy provides you more control

    // 16. Shared base classes
    // when you have a common base but can be used with some mutually exclusive behavior
    val textButton = TextButton("Eject!")
    val imageButton = ImageButton( javafx.scene.image.Image("image url"))


    // 17. Extensibility
    // Sometimes you want to extend a class because you don't own the code

    // 18. Identity
    // to share a common behavior and take advantage of polymorphism and interfaces

}

// 1.

open class Person(var firstName: String, var lastName: String) {
    fun fullName() = "$firstName $lastName"
}

data class Grade(val letter: Char, val points: Double, val credits: Double)

open class Student(firstName: String, lastName: String, var grades: MutableList<Grade> = mutableListOf()) :
    Person(firstName, lastName) {

    open fun recordGrade(grade: Grade) {
        grades.add(grade)
    }
}

open class BandMember(firstName: String, lastName: String): Student(firstName, lastName){
    open val minimumPracticeTime: Int
        get(){ return 2 }
}

open class OboePlayer(firstName: String, lastName: String) : BandMember(firstName, lastName){
    override val minimumPracticeTime: Int
        get(){ return super.minimumPracticeTime * 2 }

}


// 2.

fun phonebookName(person: Person): String{
    return "${person.lastName}, ${person.firstName}"
}


// 3.

fun afterClass(student: Student): String{
    return "Goes Home"
}

fun afterClass(bandMember: BandMember): String{
    return "Goes to practice"
}

// 4.
open class StudentAthlete(firstName: String, lastName: String): Student(firstName, lastName){

    private val failedClasses = mutableListOf<Grade>()

    override fun recordGrade(grade: Grade) {
        super.recordGrade(grade)

        if(grade.letter == 'F'){
            failedClasses.add(grade)
        }
    }

    val isEligible: Boolean
        get() { return failedClasses.size < 3}
}

// 8. Abstract classes

abstract class Mammal(val birthDate: String){
    abstract fun consumeFood()
}

class Human(birthDate: String): Mammal(birthDate){

    override fun consumeFood() {
        // ...
    }

    fun createBirthCertificate(){
        // ...
    }
}

// 9.

sealed class Shape{
    class Circle(val radius: Int): Shape()
    class Square(val sideLength: Int): Shape()

}

fun size(shape: Shape): Int{
    return when(shape){
        is Shape.Circle -> {shape.radius}
        is Shape.Square -> {shape.sideLength}
    }
}

open class OtherShape{
    constructor(size: Int){

    }

    constructor(size: Int, color: String): this(size) {

    }
}

open class AnotherCircle: OtherShape{

    constructor(size: Int): super(size){

    }

    constructor(size: Int, color: String): super(size, color) {

    }
}

// 11.
class Car(val carName: String){
    inner class Engine(val engineName: String){
        override fun toString(): String {
            return "$engineName in a $carName" // error can access carName, needs 'inner' to have access
        }
    }
}

// 12.

data class Privilege(val id: Int, val name: String){

    open class User(
        val userName: String,
        private val id: String,
        protected var age: Int
    )

    class PrivilegedUser(userName: String, id: String, age: Int): User(userName, id, age){

        private val privileges = mutableListOf<Privilege>()

        fun addPrivilege(privilege: Privilege){
            privileges.add(privilege)
        }

        fun hasPrivilege(privilege: Privilege): Boolean{
            return privilege in privileges
        }

        fun hasPrivilege(id: Int): Boolean{
            return privileges.map { it.id }.contains(id)
        }

        fun about(): String{
            // can access id, because is private so is only accessible inside 'User' clas
            // return "$userName, $id"
            return "$userName, $age"
            // can access age because PrivilegedUser extends User, and age is 'protected'
            // so, anything extending User can access $age
        }


    }
}

// 15.

class Team{
    private val players = mutableListOf<StudentAthlete>()
    val isEligible: Boolean
        get(){
            for (player in players){
                if (!player.isEligible){
                    return false
                }
            }
            return true
        }
}

// 16.
open class Button(){

    fun press(){
        println("I'm being pressed!")
    }
}

class ImageButton(image: javafx.scene.image.Image)
class TextButton(text: String)