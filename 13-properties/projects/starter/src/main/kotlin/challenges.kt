import kotlin.properties.Delegates

fun main(){


    // 1. rewrite with default name and lazy ingredients
    /*
        class IceCream{
            val name: String
            val ingredients: ArrayList<String>
        }
     */

    val iceCream = IceCream()

    println(iceCream.name)
    println(iceCream.ingredients.joinToString(", "))

    // 2. Car fuel tank

    val fuelTank = FuelTank()
    fuelTank.drive()
    println(fuelTank.lowLevel)
    fuelTank.drive()
    fuelTank.drive()
    fuelTank.drive()
    fuelTank.drive()
    fuelTank.drive()
    fuelTank.drive()
    fuelTank.drive()
    fuelTank.drive()
    fuelTank.drive()
    println(fuelTank.lowLevel)
    fuelTank.fill()
    println(fuelTank.lowLevel)


}



class IceCream(val name: String = "IceCream"){
    val ingredients : ArrayList<String> by lazy {
        arrayListOf("Milk", "Ice", "Cream")
    }
}

class FuelTank{
    var lowLevel = false
    var level: Double by Delegates.observable(1.0){
            property, oldValue, newValue ->
        if(level <= 0.1){
            lowLevel = true
            println("fuel = $newValue")
        } else {
            lowLevel = false
            println("fuel = $newValue")
        }
    }


    fun drive(){
        level -= 0.1
        println(level)
    }

    fun fill(){
        level = 1.0
    }

}


