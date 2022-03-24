import java.text.SimpleDateFormat
import java.util.*

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

    // 1. What is an exception
    // Exceptions are events that point to something that went wrong during program execution
    // They are represented by the Exception Java class and contain a message, and a possible cause (another event)

    // someFunction()

    // 2. Throw Exceptions
    // you can use the 'throw' keyword to rise and exception
    val spaceCraft = SpaceCraft()
    SpacePort.investigateSpace(spaceCraft)

    // 3. Handling exceptions
    // use a 'try/catch' block to avoid program termination

    // 4. Creating custom exceptions
    // You can extend the Exception class to create your own Exceptions

    // 5. Difference between Java and Kotlin exception
    // In java there are 'checked' and 'unchecked' exceptions
    // 'checked' are the exceptions you handle, and you declare them with a 'throws' keyword in your methods
    // 'unchecked' are those not declared or expected
    // all exceptions in Kotlin ar 'unchecked' you don't need to declare them in the methods but if you dont handle them
    // the program will terminate

    // 6. try as an exception
    // In Kotlin you can get values back from a 'try/catch' block,
    // just as when you 'lift' a return declaration from an 'if' condition

    val date: Date = try {
        val sdf  = SimpleDateFormat("dd/MM/yyyy")
        sdf.parse("31/12/1999")
    } catch (ex: Exception){
        Date()
    }

}

fun someFunction(){

    anotherFunction()
}

fun anotherFunction(){
    oneMoreFunction()
}

fun oneMoreFunction(){
    throw Exception("Something went wrong!")
}

class SpaceCraft {

    private var isConnectionAvailable: Boolean = false
    private var isEngineInOrder: Boolean = false
    private var fuel: Int = 0
    var isInSpace: Boolean = false

    fun launch(){
        if(fuel < 5){
            throw OutOfFuelException()
        }

        if(!isEngineInOrder){
            throw BrokenEngineException()
        }

        if(isConnectionAvailable){
            throw SpaceToEarthConnectionFailedException()
        }

        sendMessageToEarth("Trying to launch...")
        fuel -= 5
        sendMessageToEarth("I'm in space!")
        isInSpace = true

        sendMessageToEarth("I've found some extraterrestrials")
        throw AlienAttackException()
    }

    fun sendMessageToEarth(message: String){
        println("Spacecraft to Earth: $message")
    }

    fun refuel(){
        fuel += 5
        sendMessageToEarth("The fuel tank is filled.")
    }

    fun repairEngine(){
        isEngineInOrder = true
        sendMessageToEarth("The engine is in order.")
    }

    fun fixConnection() {
        isConnectionAvailable = true
        sendMessageToEarth("Hello earth, can you hear me?")
        sendMessageToEarth("Connection is established.")
    }

    fun land(){
        sendMessageToEarth("Landing...")
        isInSpace = false
    }

    // challenges
    fun overhaul(){

        if(fuel < 5){
            refuel()
        }

        if(!isEngineInOrder){
            repairEngine()
        }

        if(isConnectionAvailable){
            fixConnection()
        }
    }
}

object SpacePort{

    fun investigateSpace(spaceCraft: SpaceCraft){
        // 3.
        try {
            spaceCraft.launch()
        }
        catch(ex: SpaceCraftException){
            spaceCraft.sendMessageToEarth(ex.localizedMessage)
            spaceCraft.overhaul()
            spaceCraft.launch()
        }
        catch(ex: AlienAttackException){
            spaceCraft.sendMessageToEarth(ex.localizedMessage)
            spaceCraft.land()
        }
        finally {
            if(spaceCraft.isInSpace){
                spaceCraft.land()
            }
        }
    }

    // challenges

    fun testSetup(spaceCraft: SpaceCraft) : Boolean{
        var testResult  = false
        try {
            spaceCraft.launch()
        }
        catch(ex: SpaceCraftException){
            spaceCraft.sendMessageToEarth(ex.localizedMessage)
            when(ex){
                is OutOfFuelException -> {
                    spaceCraft.refuel()
                }
                is BrokenEngineException -> {
                    spaceCraft.refuel()
                }
                is SpaceToEarthConnectionFailedException -> {
                    spaceCraft.refuel()
                }
            }
        }
        finally {
            if(spaceCraft.isInSpace){
                spaceCraft.land()
                testResult = true
            } else {
                testResult = false
            }
        }

        return testResult
    }
}

// 4.

/*
class OutOfFuelException : Exception("Out of fuel. Can't take off")

class BrokenEngineException : Exception("The engine is broken. Can't take off!")

class SpaceToEarthConnectionFailedException: Exception("No connection with Earth. Can't take off")
*/

open class SpaceCraftException( message: String) : Exception(message)

class OutOfFuelException : SpaceCraftException("Out of fuel. Can't take off")

class BrokenEngineException : SpaceCraftException("The engine is broken. Can't take off!")

class SpaceToEarthConnectionFailedException: SpaceCraftException("No connection with Earth. Can't take off")

// challenges

class AlienAttackException: Exception("We are being attacked by aliens!"){

}