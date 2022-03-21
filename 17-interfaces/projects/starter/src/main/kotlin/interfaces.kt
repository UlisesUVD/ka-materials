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

    // 1. Introducing interfaces


    // 2. Interface syntax
    // interfaces cannot be instantiated, should be 'implemented'/'complied' by classes


    // 3. Methods in interfaces
    // Methods can have parameters and return types, parameters in methods can have default values
    val optionalDirection = OptionalDirection()
    optionalDirection.turn()
    optionalDirection.turn(Direction.RIGHT)

    // 4. Default method implementation
    // As in java 8 and later, you can have default methods implementations

    val falcon = LightFreighter()
    falcon.accelerate()
    falcon.stop()

    val enterprise = Starship()
    enterprise.accelerate()
    enterprise.stop()

    // 5. Properties in interfaces
    // You can define properties, abstract (with no initialization/get) and non-abstract

    // 6. Interface Inheritance
    // You can extend interfaces the same way you do with classes

    // 7. Implementing multiple interfaces
    // One class can only inherit from another single class, but one class can implement multiple interfaces


    // 8. Interfaces in the standard library
    // You already use interfaces all the time,  lots of types/classes from the standard library relay on interfaces


    // Iterable
    val cars = listOf("Lamborghini", "Ferrari", "Rolls-Royce")
    val numbers = mapOf("Brady" to 12, "Manning" to 18, "Brees" to 9)

    for (car in cars){
        println(car)
    }

    for (qb in numbers){
        println("${qb.key} wears ${qb.value}")
    }

    // Comparable

    val titanic = Boat()
    titanic.length = 883

    val qe2 = Boat()
    qe2.length = 963

    println(titanic > qe2)


}

// 1.
interface Vehicle{
    fun accelerate()
    fun stop()
}

// 2.
class Unicycle : Vehicle {

    var peddling = false

    override fun accelerate() {
        peddling = true
    }

    override fun stop() {
        peddling = false
    }

}

// 3.
enum class Direction{
    LEFT,
    RIGHT
}

interface DirectionalVehicle{
    fun accelerate()
    fun stop()
    fun turn(direction: Direction)
    fun description(): String
}

interface OptionalDirectionalVehicle{
    fun turn(direction: Direction = Direction.LEFT)
}

class OptionalDirection: OptionalDirectionalVehicle{
    override fun turn(direction: Direction) {
        println(direction)
    }

}

// 4.
interface SpaceVehicle{
    fun accelerate()
    fun stop(){
        println("Whoa! Slow down!")
    }
}

class LightFreighter: SpaceVehicle{
    override fun accelerate() {
        println("Proceed to hyperspace!")
    }
    // no need to override stop()
}

class Starship: SpaceVehicle{
    override fun accelerate() {
        println("Warp factor 9 please!")
    }

    override fun stop() {
        super.stop() // call the default implementation if you want to...
        println("That kind of hurt!")
    }

}

// 5.
interface VehicleProperties{
    val weight: Int // abstract
    val name: String
        get() = "Vehicle"
}

class Car: VehicleProperties{
    override val weight: Int
        get() = 1000
}

class Tank: VehicleProperties{

    override val weight: Int
        get() = 10000
    override val name: String
        get() = "Tank"
}

// 6.

interface WheeledVehicle: Vehicle{
    val numberOfWheels: Int
    val wheelSize: Double
}

class Bike: WheeledVehicle{

    var peddling = false
    var brakesApplied = false

    override val numberOfWheels = 2
    override val wheelSize = 622.0

    override fun accelerate() {
        peddling = true
        brakesApplied = false
    }

    override fun stop() {
        peddling = false
        brakesApplied = true
    }

}

// 7.
interface Wheeled{
    val numberOfWheels: Int
    val wheelSize: Int
}

class Tricycle: Wheeled, Vehicle{
    override fun accelerate() {
        println("Tricycle has accelerated")
    }

    override fun stop() {
        println("Tricycle has stopped")
    }

    override val numberOfWheels: Int
        get() = 3
    override val wheelSize: Int
        get() = 16

}

// 8.
interface SizedVehicle{
    var length: Int
}

class Boat: SizedVehicle, Comparable<Boat>{

    override fun compareTo(other: Boat): Int {
        return when{
            length > other.length -> 1
            length == other.length -> 0
            else -> -1
        }
    }

    override var length: Int = 0

}