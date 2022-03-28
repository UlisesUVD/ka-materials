import kotlinx.coroutines.*
import kotlin.math.floor

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

object BuildingYard {

  class Project(var name: String, var floors: Int)

  suspend fun startProject(projects: List<Project>) : Collection<Deferred<Building>>{
    val startTime = System.currentTimeMillis()
    val buildings = ArrayList<Deferred<Building>>()

    for(project in projects){

      buildings.add(
        coroutineScope {
          async {
            val building = Building(project.name, project.floors, scope = this)
            val cores = Runtime.getRuntime().availableProcessors()
            building.speakThroughBullhorn("The building of ${building.name} is started with $cores building machines engaged")
            building.makeFoundation().join()

            (1 .. project.floors).forEach {
              building.buildFloor(it).join()
              building.placeWindows(it)
              building.installDoors(it)
              building.provideElectricity(it)
              building.fitOut(it)
            }

            building.buildRoof().join()

            building
          }
        }
      )

    }

    buildings.awaitAll()
    return buildings

  }


  suspend fun startProject(name: String, floors: Int) {
    val startTime = System.currentTimeMillis()
    val building = withContext(Dispatchers.Default){
      val building = Building(name, scope = this)
      val cores = Runtime.getRuntime().availableProcessors()
      building.speakThroughBullhorn("The building of ${building.name} is started with $cores building machines engaged")
      building.makeFoundation().join()

      (1 .. floors).forEach {
        building.buildFloor(it).join()
        building.placeWindows(it)
        building.installDoors(it)
        building.provideElectricity(it)
        building.fitOut(it)
      }

      building.buildRoof().join()

      building
    }

    if(building.floors == floors){
      building.speakThroughBullhorn("${building.name} is ready")
    }

    building.speakThroughBullhorn("${building.name} is ready in ${System.currentTimeMillis() - startTime}!")

  }

}
