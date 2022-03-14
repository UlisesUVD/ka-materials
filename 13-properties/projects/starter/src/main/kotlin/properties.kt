import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

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

    // 1. Constructor properties
    val contact = Contact("Grace Murray", "grace@navy.mil")
    val fullName = contact.fullName
    val email = contact.email

    contact.fullName = "Grace Hopper"
    val grace = contact.fullName // Grace Hopper
    println(grace)

    val contact2 = Contact2("Grace Murray", "grace@navy.mil")

    // contact2.email = "test@gmail.com", val property cannot be reassigned

    // 2. Default properties
    val contact3 = Contact3("Grace Murray", "grace@navy.mil")
    println(contact3.type)

    val workContact = Contact3("Grace Murray", "grace@navy.mil", "Work")
    println(workContact.type)

    // 3. Property initializers. You can initialize a val assigning a value
    // or declaring it and assigning its value in init{}

    val person = Person("Grace", "Hopper")
    println("Full Name: ${person.fullName}")

    val address = Address()

    println("city: ${address.city}")

    // 4. Custom getter

    val tv = TV(53.93, 95.87)
    println("TV size = ${tv.diagonal}\" ")

    // 5. Custom setter
    // Change val to var

    tv.diagonal = 70
    println(tv.height)
    println(tv.width)

    // 6. Companion Object properties

    val level1 = Level(1, "Chameleon", true)
    val level2 = Level(2, "Squid", false)
    val level3 = Level(3, "Chupacabra", false)

    val level4 = Level(4, "Yeti", false)

    println(Level.highestLevel)
    //println(Level.getHighestLevel())

    // 7. Delegated Properties, the property read/write/init is passed to some kind of utility

    // 8. Observable Properties
    val delegatedLevel1 = DelegatedLevel(1, "Chameleon")
    val delegatedLevel2 = DelegatedLevel(2, "Squid")

    println(DelegatedLevel.highestLevel)
    delegatedLevel2.unlocked = true
    println(DelegatedLevel.highestLevel)

    // 9. Limiting a variable
    // you can add a restriction to allow the assignment of a value in a property or not

    val lightBulb = LightBulb()
    lightBulb.current = 50
    println(lightBulb.current)
    lightBulb.current = 20
    println(lightBulb.current)

    // 10. Lazy properties, do not waste time and resources on a property until is required
    val circle = Circle(5.0)

    println(circle.circumference)
    println(circle.area)

    // 11. late-init. "I promise I'm gonna initialize this before using it"

    val lamp = Lamp()

    // println(lamp.bulb.current) // kotlin.UninitializedPropertyAccessException, because is not initialized yet

    lamp.bulb = LightBulb()
    println(lamp.bulb.current)

    // 12. Extension properties. You can add properties to classes already defined (yours or from third party)
    // see ext.kt file

    println(circle.diameter) // diameter property added to this file's Circle class by ext.kt
}


// 1.
class Contact(var fullName: String, var email: String)

class Contact2(var fullName: String, val email: String)

// 2.

class Contact3(var fullName: String, var email: String, var type: String = "Friend")

// 3.
class Person(var firstName: String, var lastName:String){
    val fullName = "$firstName $lastName"
}

class Address{
    var address1: String
    var address2: String? = null
    var city: String = ""
    var state: String

    init {
        address1 = ""
        state = ""
    }
}

// 4.

class TV(var height: Double, var width: Double){
    var diagonal : Int
        get() {
            val result = sqrt( height.pow(2.0) + width.pow(2.0))
            return result.roundToInt()
        }
        set(value){
            val ratioWidth = 16.0
            val ratioHeight = 9.0

            val ratioDiagonal = Math.sqrt(ratioWidth * ratioWidth + ratioHeight * ratioHeight)

            height = value.toDouble() * ratioHeight /ratioDiagonal
            width = height * ratioWidth / ratioHeight
        }
}

// 6.

class Level(var id: Int, var boss: String, var unlocked: Boolean){
    companion object{
        @JvmStatic var highestLevel = 1

        // Using JvmStatic allows us to use getHighestLevel()
    }
}

// 8.

class DelegatedLevel(val id: Int, var boss: String) {
    companion object {
        var highestLevel = 1
    }
    var unlocked: Boolean by Delegates.observable(false)
    { _: KProperty<*>, oldValue: Boolean, newValue: Boolean ->
        if(newValue && id > highestLevel){
            highestLevel = id
        }

        println("$oldValue -> $newValue")
    }
}

// 9.
class LightBulb{
    companion object{
        const val maxCurrent = 40
    }

    var current by Delegates.vetoable(0){
        property, oldValue, newValue ->
        if(newValue > maxCurrent){
            println("Current too high, falling back to previous setting")
            false
        } else {
            true
        }
    }
}

// 10.

class Circle(var radius: Double = 0.0){
    val pi : Double by lazy{
        ((4.0 * Math.atan(1.0/5.0)) - Math.atan(1.0/ 239.0)) * 4
    }

    // mini exercise
    val area : Double by lazy {
        Math.PI*radius.pow(2)
    }

    val circumference: Double
        get() = pi * radius * 2
}

// 11.
class Lamp{
    lateinit var bulb: LightBulb
}