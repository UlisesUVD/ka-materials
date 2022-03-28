import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

class Building(
  val name: String,
  var floors: Int = 0,
  private val scope: CoroutineScope) {

  suspend fun makeFoundation() = scope.launch{
    delay(300)
    speakThroughBullhorn("The foundation of $name is ready")
  }

  suspend fun buildFloor(floor: Int) = scope.launch{
    delay(100)
    speakThroughBullhorn("The $floor'th floor of $name is raised")
    ++floors
  }

  suspend fun placeWindows(floor: Int) = scope.launch{
    delay(100)
    speakThroughBullhorn("Windows are placed on the $floor'th floor of $name")
  }

  suspend fun installDoors(floor: Int) = scope.launch{
    delay(100)
    speakThroughBullhorn("Doors are installed on the $floor'th floor of $name")
  }

  suspend fun provideElectricity(floor: Int) = scope.launch{
    delay(100)
    speakThroughBullhorn("Electricity is provided on the $floor'th floor of $name")
  }

  suspend fun buildRoof() = scope.launch{
    delay(200)
    speakThroughBullhorn("The roof of $name is ready")
  }

  suspend fun fitOut(floor: Int) = scope.launch{
    delay(200)
    speakThroughBullhorn("The $floor'th floor of $name is furnished")
  }

  fun speakThroughBullhorn(message: String) = println(message)

}
