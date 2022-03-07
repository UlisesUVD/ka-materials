import kotlin.random.Random

fun main(){

    val user = StoreUser("user1", "user1@gmail.com", ShoppingCart())

    val shirts = (0..10).map {
        TShirt(
            size = listOf("S", "M", "L").random(),
            color = listOf("Black", "Blue", "Withe").random(),
            price = Random.nextDouble(20.0) + 10.0,
            image = listOf("Logo", "",).random()
        )
    }

    val address = Address("my home", "my street", "my city", "my zipcode")
    user.shoppingCart.address = address


    repeat(3){
        user.shoppingCart.items.add( shirts[Random.nextInt(11)])
    }

    println(user.shoppingCart.total())

}


data class TShirt(var size: String, var color: String, var price: Double, var image: String = "")

data class StoreUser(var name:String, var email: String, var shoppingCart: ShoppingCart)

data class Address(var name: String, var street: String, var city: String, var zipcode: String)

data class ShoppingCart(var items: MutableList<TShirt> = mutableListOf(), var address: Address? = null){

    fun total():Double{
        println(items.joinToString(", "))
        return items.sumByDouble { tShirt -> tShirt.price   }
    }
}