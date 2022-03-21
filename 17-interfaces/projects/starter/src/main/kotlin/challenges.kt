
fun main(){

    val allPets = listOf<Pet>(Dog(), Dog(), Cat(), Fish(), Bird(), Bird(), Cat(), Fish(), Bird(), Dog(), Cat())

    val flyingPets = mutableListOf<FlyingPet>()
    val swimmingPets = mutableListOf<SwimmingPet>()
    val walkingPets = mutableListOf<WalkingPet>()


    for(pet in allPets){

        when(pet){
            is WalkingPet -> {

                pet.walk()
                pet.rest()
                pet.feed()

                walkingPets.add(pet)

            }

            is SwimmingPet -> {

                pet.dropIn()
                pet.feed()

                swimmingPets.add(pet)
            }

            is FlyingPet -> {

                pet.lockUp()
                pet.feed()
                pet.letOut()

                flyingPets.add(pet)

            }
        }
    }

    println(walkingPets.joinToString(","))
    println(swimmingPets.joinToString(","))
    println(flyingPets.joinToString(","))

}

interface Pet {
    fun feed()
}

interface FlyingPet : Pet {
    var caged : Boolean

    fun lockUp()
    fun letOut()
}

interface SwimmingPet : Pet {

    var wet: Boolean

    fun dropIn()
    fun takeOut()
}

interface WalkingPet: Pet{

    var walking: Boolean

    fun walk()
    fun rest()
}

class Dog: WalkingPet{

    override var walking: Boolean  = false

    override fun walk() {
        walking = true
        println("Walking...")
    }

    override fun rest() {
        walking = false
        println("Resting...")
    }

    override fun feed() {
        println("Eating...")
    }

    override fun toString(): String {
        return "Dog(walking=$walking)"
    }

}

class Cat: WalkingPet{

    override var walking: Boolean  = false

    override fun walk() {
        walking = true
        println("Walking...")
    }

    override fun rest() {
        walking = false
        println("Resting...")
    }

    override fun feed() {
        println("Eating...")
    }

    override fun toString(): String {
        return "Cat(walking=$walking)"
    }


}

class Bird : FlyingPet{

    override var caged: Boolean = false

    override fun lockUp() {
        caged = true
        println("Locked up ...")
    }

    override fun letOut() {
        caged = false
        println("let loose...")
    }

    override fun feed() {
        println("Eating...")
    }

    override fun toString(): String {
        return "Bird(walking=$caged)"
    }
}

class Fish: SwimmingPet{

    override var wet: Boolean = false

    override fun dropIn() {
        wet = true
        println("In water...")
    }

    override fun takeOut() {
        wet = false
        println("Not in water!...")
    }

    override fun feed() {
        println("Eating...")
    }

    override fun toString(): String {
        return "Fish(walking=$wet)"
    }
}