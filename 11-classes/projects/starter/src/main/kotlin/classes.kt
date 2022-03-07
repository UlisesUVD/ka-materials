import javax.print.attribute.standard.MediaSize
import kotlin.random.Random

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


    // 1.
    val john = Person(firstName = "Johnny", lastName = "Appleseed")
    println(john.fullName)

    // 2. Reference types, vars are references to the instance in the heap, are not the instance
    var var1 = SimplePerson("John")
    var var2 = var1

    println("var1 = $var1")
    println("var2 = $var2")

    // 3. Working with references

    var homeOwner = john
    john.firstName = "John" // changed from johnny to john

    println(homeOwner.fullName)
    println(john.fullName)
    // both references are aware of the change, even when the change is applied through only one reference

    homeOwner.lastName = "NotAppleseed"
    println(homeOwner.fullName)
    println(john.fullName)
    homeOwner.lastName = "Appleseed"

    // 4. Object Identity
    val impostorJohn = Person(firstName = "John", lastName = "Appleseed")

    println(john === homeOwner) // true, because both references point to the same instance
    println(john === impostorJohn) // false, cause it contains the same values but is not pointing to the same instance
    println(impostorJohn === homeOwner)

    homeOwner = impostorJohn

    println(john === homeOwner) // false, because reference was updated to point to the impostor instance

    homeOwner = john
    println(john === homeOwner)

    var impostors = (0..100).map {
        Person("John", "Appleseed")
    }

    println(impostors.contains(john)) // false, does not contain the "john" reference

    // now insert "john" somewhere in among the impostors
    val mutableImpostors = mutableListOf<Person>()
    mutableImpostors.addAll(impostors)
    mutableImpostors.set(Random.nextInt(100), john)
    println(impostors.contains(john)) // true, now is there in some place

    // 5. Find a person in a list of peopple

    val group: MutableList<Person> = (1..10).map {
        Person("Andy", "Ferent")
    }.toMutableList()

    val person = Person("Andy", "Ferent")

    if (Random.nextInt(2) == 1) {
        group.add(person)
        println("Inserted person")
    }
    println(memberOf(person, group))

    // 6. Methods and Mutability
    val jane = Student("Jane", "Appleseed")
    val history = Grade("B", 9.0, 3.0)
    val math = Grade("A", 16.0, 4.0)

    jane.recordGrade(history)
    jane.recordGrade(math)

    // 7. GPA: write a getter to obtain the gpa of the student; gpa = total points / total credits

    println(jane.gpa)

    // 8. Data classes
    val albert = ComparableStudent("Albert", "Einstein", 1)
    val richard = ComparableStudent("Richard", "Feyman", 2)

    val albertCopy = albert.copy()

    println(albert)
    println(richard)

    println(albert == richard)
    println(albert == albertCopy)
    println(albert === albertCopy)

    val marie = StudentData("Marie", "Curie", 1)
    val emmy = StudentData("Emmy", "Noether", 2)
    val marieCopy = marie.copy()
    println(marie)
    println(emmy)

    println(marie == emmy)
    println(marie == marieCopy)
    println(marie === marieCopy)

    // deconstruction
    val (firstName, lastName, id) = marie
    println("ComparableStudent(firstName='$firstName', lastName='$lastName', id=$id)")
}

// 1. Creating classes
class Person(var firstName: String, var lastName: String) {
    val fullName get() = "$firstName $lastName"
}

// 2.
class SimplePerson(var name: String)

// 5.
fun memberOf(person: Person, group: List<Person>): Boolean {
    return group.contains(person)
}

// 6. Methods and Mutability
class Grade(
    val letter: String,
    val points: Double,
    val credits: Double
)

class Student(
    val firstName: String,
    val lastName: String,
    val grades: MutableList<Grade> = mutableListOf(),
    var credits: Double = 0.0
) {
    fun recordGrade(grade: Grade) {
        grades.add(grade)
        credits += grade.credits
    }

    val gpa: Double
        get() = grades.fold(0.0) { acc: Double, current: Grade ->
            acc + current.points
        } / grades.fold(0.0) { acc: Double, current: Grade ->
            acc + current.credits
        }
}

// 8.

class ComparableStudent(
    var firstName: String,
    var lastName: String,
    var id: Int
) {

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + id
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if(other == null) return false
        if (javaClass != other.javaClass) return false

        val obj = other as ComparableStudent

        if (firstName != obj.firstName) return false
        if (lastName != obj.lastName) return false
        if (id != obj.id) return false

        return true
    }

    override fun toString(): String {
        return "ComparableStudent(firstName='$firstName', lastName='$lastName', id=$id)"
    }


    fun copy(
        firstName: String = this.firstName,
        lastName: String = this.lastName,
        id: Int = this.id
    ) = ComparableStudent(firstName, lastName, id)
}

// better to use data classes

data class StudentData(
    var firstName: String,
    var lastName: String,
    var id: Int
)

// it implements by default hashCode, equals, copy and toString
