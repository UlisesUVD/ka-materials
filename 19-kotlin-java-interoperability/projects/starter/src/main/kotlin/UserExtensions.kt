

// This annotation avoids the default UserExtensionsKt name
// And will generate the one you define
@file:JvmName("UserExtensions")
// as a custom property getter
val User.fullName: String
    get() = "$firstName $lastName"

// as a function
fun User.completeName(): String {
    return "$firstName, $lastName"
}

// more arguments
fun User.sayHello(greeting: String): String {
    return "$greeting, I'm $firstName, $lastName"
}

fun User.addressOfType(type: AddressType): Address?{
    return addresses?.firstOrNull {
        it.addressType == type
    }
}

fun User.addOrUpdateAddress(address: Address){

    val existingOfType = addressOfType(address.addressType)

    if(existingOfType != null){
        addresses?.remove(existingOfType)
    }

    addresses?.add(address)

}
