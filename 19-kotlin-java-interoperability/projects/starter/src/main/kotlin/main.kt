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
    println("Hello Kotlin Main!")

    // 1. Mixing Java and Kotlin code
    // Kotlin was created to be able to run in the JVM so your code will be compiled into byte code
    // that will be run in the JVM just as Java, hence the interoperability

    // 2. Getter and Setters
    // highlight the attributes of the user instance to se
    // the autogenerated setters and getters

    val user = User()
    user.firstName = "A" // setFirstName("A")
    val firstName = user.firstName // getFirstName()

    user.firstName = "Bob"
    user.lastName = "Barker"


    println("User info: $user")

    // 3. Adding a Kotlin class as a Java  property

    val billingAddress = Address(
        "123 Fake Street", "4th floor", "Los Angeles",
        "CA", "90291", AddressType.Billing
    )

    println("Billing Address:\n$billingAddress\n")

    // 4. Adding extension functions to a Java class
    // see UserExtensions.kt

    user.addOrUpdateAddress(billingAddress)
    println("User info after adding address:\n$user\n")

    val shippingAddress = Address(
        "987 Unreal Drive", null,
        "Burbank", "CA", "91523", AddressType.Shipping
    )

    user.addOrUpdateAddress(shippingAddress)

    println("User info after adding addresses:\n$user")

    // 5. Free Functions
    // See LabelPrinter.kt
    println("Shipping Label:")
    printLabelFor(user)

    // 6. Java nullability annotations
    // Since Java 8, we have the Optional type but, when dealing with nullable values annotations are still the way to go

    val anotherUser = User()

    // this will cause an error
    // anotherUser.addresses = null
    //println("Another User has ${anotherUser.addresses.count()} addresses")

    // after adding @Nullable to getAddress() you will need to add a safe operator to addresses
    // println("Another User has ${anotherUser.addresses?.count()} addresses")

    // after adding @NotNull to getAddress() you will need to remove the unnecessary safe operator
    println("Another User has ${anotherUser.addresses.count()} addresses")

    println("Another User name: ${anotherUser.firstName ?: "(Not set)" }")

    // 7. Making your Kotlin Code Java-Friendly
    // see main.java

    // 8. Accessing nested kotlin objects
    // see main java

    // 9. "Static" values and functions from kotlin

    println("Sample first line : ${Address.sampleFirstLine}")

    println("Sample canadian address : ${Address.canadianSample(AddressType.Billing)}")



}