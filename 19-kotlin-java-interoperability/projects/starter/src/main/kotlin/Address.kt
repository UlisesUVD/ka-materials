enum class AddressType{
    Billing,
    Shipping,
    Gift
}

data class Address @JvmOverloads constructor(
    // @JvmField avoids getter/setter auto-generation and exposes the field
    // that by default won't be accessible for set because it is already defined in the constructor and is inmutable

    @JvmField val streetLine1: String,
    @JvmField val streetLine2: String?,
    @JvmField val city: String,
    @JvmField val stateOrProvince: String,
    @JvmField val postalCode: String,
    @JvmField var addressType: AddressType,
    @JvmField val country: String = "United States"
) {
    // TODO

    fun forPostalLabel(): String{
        var printedAddress = streetLine1
        streetLine2?.let { printedAddress + "\n$it" }
        printedAddress += "\n$city, $stateOrProvince $postalCode"
        printedAddress += "\n${country.toUpperCase()}"
        return printedAddress
    }

    override fun toString(): String {
        return forPostalLabel()
    }

    object JSONKeys{
        // vals need to be 'const' in order to be accessed as a static field from java
        const val streetLine1 = "street_1"
        const val streetLine2 = "street_2"
        const val city = "city"
        const val stateOrProvince = "state"
        const val postalCode = "zip"
        const val addressType = "type"
        const val country = "country"
    }

    companion object{
        // vals need to be 'const' in order to be accessed as a static field from java
        const val sampleFirstLine = "123 Fake Street"

        @JvmStatic
        fun canadianSample(type: AddressType): Address {
            return Address(
                sampleFirstLine,
                "4th floor",
                "Vancouver",
                "BD",
                "A3G 4B2",
                type,
                "Canada"
            )
        }
    }

}