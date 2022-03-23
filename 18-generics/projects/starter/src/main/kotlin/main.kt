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


    // 1. Anatomy of standard library generic types
    // You use generic types whenever you use the Lists and 'Maps


    // 2. Lists

    val names: List<String> = listOf("Bob", "Ted", "Alice")
    println("Names $names")
    val firstName = names.first()

    println(firstName)

    // You don't need to add <Type>, Kotlin can infer the type from the elements in the initialization

    val things = mutableListOf(1, 2) //<Int>
    // Error , because initial items are Int, the inferred type is Int
    // things.add("Steve")
    println("Things $things")

    val moreThings = mutableListOf(1, 2, "Steve") //<Any>
    // There are Int and String items in the initialization of this MutableList<>
    // So the inferred type would be the first common class upward Int and String hierarchy
    // in this case the type would be Any, every Type is a child class of Any

    println("More things: $moreThings")

    // 3. Maps

    // interface Map<K, out V>
    // so keys must be of the same type, and values must be of a type that inherits from V

    val map = mapOf(
        Pair("one", 1),
        Pair("two", "II"),
        Pair("three", 3.0)
    )

    // incorrect Type key
    // val one = map.get(1)
    // val one = map[1]

    // The fact that you have 2 Generics to work with in Maps let you perform a lot of operations on keys and values

    val valuesForKeys = map.keys
        .filter {
            it.contains("e")
        }
        .map {
            "Value for $it: ${map[it]}"
        }

    println("Values for keys with E: $valuesForKeys")

    // 4. Extension functions on types with generic constraints

    println("Names: ${names.toBulletedList()}")

    // Error, remember that the extension requires a Generic of type String and 'things' are Int
    // println("Things: ${things.toBulletedList()}")

    // Add another 'toBulletedList()' to work with a more "generic" type
    println("More Things with a broader generic type: ${moreThings.toBulletedList()}")

    // 5. Creating your own generic constraints
    val cheapThings =
        listOf(
            CheapThing("Cinder block table"),
            CheapThing("Box of old books"),
            CheapThing("Ugly old couch")
        )

    val cheapMover = Mover(cheapThings)

    cheapMover.moveEverythingToTruck(null)
    cheapMover.moveEverythingIntoNewPlace()
    cheapMover.finishMove()

    val television = BreakableThing("Flat-Screen Television")
    val breakableThings = listOf(
        television,
        BreakableThing("Mirror"),
        BreakableThing("Guitar")
    )

    val expensiveMover = Mover(breakableThings)

    television.smash()

    expensiveMover.moveEverythingToTruck(CardBoardBox())
    expensiveMover.moveEverythingIntoNewPlace()
    expensiveMover.finishMove()

    // 6. Interfaces
    // You can use interfaces implemented by your custom types to make your Generic implementations more 'generic'

    // 7. Generic Interfaces
    // this is an interface constrained to a generic type

    // 8. Generic Type Variance (a.k.a. in and out declarations)

    val ints = listOf(1, 2, 3)
    val numbers: List<Number> = ints

    // this wont work
    // val moreInts: List<Int> = numbers

    // Covariance:
    // because Lists are declared as 'interface List<out E> : Collection<E>'
    // yo can get a collection of a supertype of the generic type
    // in this case you can pass a List<Int> to a List<Number>
    // because Int is a subtype of Number


    val mutableInts = mutableListOf(1, 2, 3)
    // val mutableNumbers: MutableList<Number> = mutableInts

    // Invariant:
    // You cannot make assumptions with MutableList types because is not declared as 'out' or 'in'
    // everything must be handled exactly as the type it was defined

    // Contravariant:
    // You can use 'in' T, to handle subtypes for example if you use a Comparable<in Number>
    // in some point you can compare that Number to an Int because you can create an Int from a number

}

// 4.

/*
fun List<String>.toBulletedList(): String{
    val separator = "\n -"
    return this.map { " $it" }.joinToString(separator, prefix = separator, postfix = "\n")
}
*/

// To use a generic in a function you need to declare such <GenericTye> at the beginning to state that this function
// will work with generics and to consider that unknown/custom type, will be provided when required

fun <T> List<T>.toBulletedList(): String {
    val separator = "\n -"
    return this.map { " $it" }.joinToString(separator, prefix = separator, postfix = "\n")
}

// 5.

class Mover<T: Checkable>(
    thingsToMove: List<T>,
    val truckHeightInInches: Int = 12 * 12
) {
    private var thingsLeftInOldPlace = mutableListOf<T>()
    private var thingsInTruck = mutableListOf<Any>()
    private var thingsInNewPlace = mutableListOf<T>()
    private var thingsWhichFailedCheck = mutableListOf<T>()

    init {
        thingsLeftInOldPlace.addAll(thingsToMove)
    }

    private fun moveContainerToTruck(container: Container<T>){
        thingsInTruck.add(container)
        println("Moved a container with your ${container.contents().toBulletedList()} to the truck")
    }

    fun moveEverythingToTruck(startingContainer: Container<T>?) {

        var currentContainer = startingContainer

        while (thingsLeftInOldPlace.count() > 0) {

            val item = thingsLeftInOldPlace.removeAt(0)

            if(item.checkIsOk()){

                if(currentContainer != null){

                    if(!currentContainer.canAddAnotherItem()){
                        moveContainerToTruck(currentContainer)
                        currentContainer = currentContainer.getAnother()
                    }

                    currentContainer.addItem(item)
                    println("Packed your $item")
                } else {
                    thingsInTruck.add(item)
                    println("Moved your $item to the truck!")
                }

                thingsInTruck.add(item)
                println("Moved your $item to the truck")
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item to the truck")
            }
        }

        currentContainer?.let {  moveContainerToTruck(it) }
    }

    fun moveEverythingIntoNewPlace() {

        val containers = thingsInTruck.filterIsInstance<Container<T>>()

        for (container in containers){
            thingsInTruck.remove(container)
            while (container.canRemoveAnotherItem()){
                val itemContainer = container.removeItem()
                println("Unpacked your $itemContainer !")
                tryToMoveItemIntoNewPlace(itemContainer)
            }
        }

        while (thingsInTruck.count() > 0) {

            val item = thingsInTruck.removeAt(0) as? T
            if(item != null){
                tryToMoveItemIntoNewPlace(item)
            } else {
                println("Something in the truck was not of the expected generic type: $item")
            }
        }
    }

    fun tryToMoveItemIntoNewPlace(item: T){
        if(item.checkIsOk()){
            thingsInNewPlace.add(item)
            println("Moved your $item into your new place!")
        } else {
            thingsWhichFailedCheck.add(item)
            println("Could not move your $item into your new place!")
        }
    }

    fun finishMove() {
        println("OK, we finished! We were able to move your: ${thingsInNewPlace.toBulletedList()}")
        if(thingsWhichFailedCheck.isNotEmpty()){
            println("But we need to talk about your ${thingsWhichFailedCheck.toBulletedList()}")
        }
    }
}


class CheapThing(val name: String) : Checkable{
    override fun checkIsOk(): Boolean {
        return true
    }

    override fun toString(): String {
        return name
    }
}

class BreakableThing(val name: String, var isBroken: Boolean = false): Checkable{
    fun smash(){
        isBroken = true
    }

    override fun checkIsOk(): Boolean {
        return !isBroken
    }

    override fun toString(): String {
        return name
    }
}

// 6.

interface Checkable{
    fun checkIsOk(): Boolean
}

// 7.

interface Container<T>{

    fun canAddAnotherItem(): Boolean

    fun addItem(item: T)

    fun canRemoveAnotherItem(): Boolean

    fun removeItem(): T

    fun getAnother(): Container<T>

    fun contents(): List<T>
}

class CardBoardBox: Container<BreakableThing>{

    private var items = mutableListOf<BreakableThing>()

    override fun contents(): List<BreakableThing> {
        return items.toList()
    }

    override fun canAddAnotherItem(): Boolean {
        return items.count() < 2
    }

    override fun addItem(item: BreakableThing) {
        items.add(item)
    }

    override fun canRemoveAnotherItem(): Boolean {
        return items.count() > 0
    }

    override fun removeItem(): BreakableThing {

        var lastItem = items.last()
        items.remove(lastItem)
        return lastItem
    }

    override fun getAnother(): Container<BreakableThing> {
        return CardBoardBox()
    }


}