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


    // 1. sentinel values
    var error = 0 // no error
    var job = "" // unemployed

    // 2. Introducing nullables
    var errorCode: Int?

    errorCode = 100
    errorCode = null


    // 3. checking for null
    var result: Int? = 30
    println(result)
    // no problem

    // println(result + 1)
    // oops!
    // you are trying to add an int to a nullable that may contain a value
    // not to the value itself

    // 4. not null assertion operator

    val authorName : String? = "Joe Howard"
    val authorAge: Int? = 24


    val ageAfterBirthDay = authorAge!! + 1
    println("After teir next birthday, the author would be $ageAfterBirthDay")

    // 5. Smart casts

    var noNullableAuthor: String
    var nullableAuthor: String?

    if(authorName != null){
        noNullableAuthor = authorName
    } else {
        nullableAuthor = authorName
    }

    // 6. Safe calls

    var nameLength = authorName?.length
    println("Author's name has a length of $nameLength")


    // 7. let() function with safe operator
    authorName?.let {
        noNullableAuthor = authorName
    }

    // 8. Elvis operator ?:

    var nullableInt: Int? = 10
    var mustHaveResult = nullableInt ?: 0

    // is the short way of
    var nullableInt2: Int? = 10
    var mustHaveResult2 = if (nullableInt != null) nullableInt else 0
}