package main.kotlin.challenges

fun main() {

    // 1.

    var familyMembers = listOf<FullName>(
        FamilyMember("Don", "Señor"),
        FamilyMember("Doña", "Señora"),
        FamilyMember("Hijo", "Señor Señora"),
        FamilyMember("Hija", "Señor Señora")
    )

    for (member in familyMembers) {
        println(member.getFullName())
    }

    //
    val vehicle1 = Vehicle("Vino", "Yamaha", 40)
    val vehicle2 = Vehicle("Corolla", "Toyota", 58)
    val vehicle3 = Vehicle("Cascadia", "Freightliner", 150)


}

// 1.

interface FullName {

    var firstName: String
    var lastName: String
    var name: String
    fun getFullName(): String
}

class FamilyMember(override var firstName: String, override var lastName: String) : FullName {
    override var name: String = "$firstName $lastName"
    override fun getFullName(): String {
        return name
    }
}

// 2.

interface Checkable {
    var verifyingFunction: (value1: Int, value2: Int) -> Boolean
    fun checkIsOk(): Boolean
}

class Vehicle(var model: String, var brand: String, var height: Int) : Checkable {

    override var verifyingFunction: (Int, Int) -> Boolean = { value1: Int, value2: Int ->  Boolean
        value1 <= value2
    }


    override fun checkIsOk(): Boolean {
        return verifyingFunction()
    }

    override fun toString(): String {
        return "Vehicle(model='$model', brand='$brand', height=$height)"
    }

}

interface Container<Vehicle> {

    var vehicles: ArrayList<Vehicle>

    fun fill(item: Vehicle): Boolean

    fun empty(): Vehicle?

    fun getAnother(): Container<Vehicle>
}

class ShippingContainer<T: Checkable> : Container<T> {

    override var vehicles: ArrayList<T> = ArrayList(1)

    override fun fill(item: T): Boolean {
        return if (vehicles.isNotEmpty()) {
            println("Container already used")
            false
        } else {
            vehicles.add(item)
            println("Vehicle added to container")
            true
        }
    }

    override fun empty(): T? {
        return if (vehicles.isNotEmpty()) {
            println("Removing vehicle")
            vehicles.removeAt(0)
        } else {
            println("Container is empty")
            null
        }
    }

    override fun getAnother(): Container<T> {
        return ShippingContainer()
    }

}

class Mover<T : Checkable>(
    vehiclesToMove: List<T>,
    val shipHeightInInches: Int = 12 * 12
) {

    private var acceptedContainers: MutableList<Container<T>> = mutableListOf()
    private var rejectedContainers: MutableList<Container<T>> = mutableListOf()
    private var thingsToMove: MutableList<T> = mutableListOf()
    private var container: Container<T>

    init {
        thingsToMove.addAll(vehiclesToMove)
        container = ShippingContainer()
    }


    fun moveInAllItems(){
        while (thingsToMove.isNotEmpty()){

            val item = thingsToMove.removeAt(0)

            if(item.checkIsOk()){
                if(container.fill(item)){
                    acceptedContainers.add(container)
                } else {
                    container = container.getAnother()
                    container.fill(item)
                    acceptedContainers.add(container)
                }
            } else {
                if(container.fill(item)){
                    rejectedContainers.add(container)
                } else {
                    container = container.getAnother()
                    container.fill(item)
                    rejectedContainers.add(container)
                }
            }
        }
    }

}

