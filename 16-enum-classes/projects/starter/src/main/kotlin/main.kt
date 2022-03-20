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

    println("Hello enums")


    // 1. Your first enum class
    for (day in DayOfTheWeek.values()) {
        println("DAy ${day.ordinal} is ${day.name}")
    }

    val dayIndex = 0
    val dayAtIndex = DayOfTheWeek.values()[dayIndex]
    println("Day at $dayIndex is $dayAtIndex")


    val tuesday = DayOfTheWeek.valueOf("Tuesday")
    println("Tuesday is day ${tuesday.ordinal}")


    try {

        val notADay = DayOfTheWeek.valueOf("Potato")
        println("not a day: $notADay")

    } catch (ex: Exception) {
        println(ex.message)
    }

    // 2. Enum class properties and functions
    for (day in DayOfTheWeek.values()) {
        println("Day ${day.name} is weekend? : ${day.isWeekend}")
    }

    println(DayOfTheWeek.today())

    val today = DayOfTheWeek.today()
    val isWeekend = "Today is ${today.name} so it is${if (today.isWeekend) "" else " not"} weekend"
    println(isWeekend)

    println("Days until Sunday: ${DayOfTheWeek.daysUntil(DayOfTheWeek.Sunday)}")
    println("Days until Wednesday: ${DayOfTheWeek.daysUntil(DayOfTheWeek.Wednesday)}")

    // 3. Using when with enum classes
    when (today) {
        DayOfTheWeek.Monday -> println("I don't care if $today's blue")
        DayOfTheWeek.Tuesday -> println("$today's gray")
        DayOfTheWeek.Wednesday -> println("And  $today, too")
        DayOfTheWeek.Thursday -> println("$today I don't care 'bout you")
        DayOfTheWeek.Friday -> println("It's $today. I'm in love")
        DayOfTheWeek.Saturday -> println("$today, wait...")
        DayOfTheWeek.Sunday -> println("$today always come too late")
        else -> println("I don't feel like singing")
        // enums are exhaustive, so you won't need an else but is a good idea if you are
        // updating the values of the enum in case you miss something
    }

    // 4. Sealed Classes VS Enum Classes

    // Sealed classes are abstract, you need an instance of the subclasses

    // Sealed classes can have abstract members, which must be implemented by all subclasses

    // Enum classes provide a single instance of each case

    // You can't make direct subclasses of a sealed class outside the file where it is defined

    // 5. Created a sealed class

    // with enum classes you get a default 'name' and 'ordinal' attributes that provide human-readable data
    // with sealed classes you need to provide some 'toString()' implementation

    println("You have got some ${AcceptedCurrency.Crypto()}")

    val currency = AcceptedCurrency.Crypto()
    println("You have got some ${currency.name}")

    currency.amount = .27541f

    println("${currency.amount} is equal to ${currency.totalValue()} dollars")

    // 6. Enumerations as a state machine
    // check Downloader.kt

    Downloader().downloadData(
        "foo.com/bar",
        { state: DownloadState? ->
            Unit
            when (state) {
                DownloadState.Idle -> println("Download has not yet started.")
                DownloadState.Starting -> println("Starting download...")
                DownloadState.InProgress -> println("Downloading data...")
                DownloadState.Error -> println("An error occurred. Download terminated")
                DownloadState.Success -> println("Download completed successfully")
                null -> println("No download state yet")
            }
        },
        { error: Error?, data: List<Int>? ->
            Unit
            error?.let { println("Got error: ${error.message}") }
            data?.let { println("Got list with ${data.size} items") }
        }
    )

    // 7. Nullables and enums
    // check Downloader.kt and update callback functions

    // Challenges
    println("Days until next weekend ${DayOfTheWeek.daysUntilNextWeekend()}")


    val currency1 = AcceptedCurrency.Crypto()
    currency1.amount = 10.0f
    val currency2 = AcceptedCurrency.Euro()
    currency2.amount = 20.0f
    println(AcceptedCurrency.sumCurrencies(currency1, currency2))

    AcceptedCurrency.enoughFunds(listOf(currency1, currency2), 25374.2f)

}


// 1.

/*
enum class DayOfTheWeek {
  Monday,
  Tuesday,
  Wednesday,
  Thursday,
  Friday,
  Saturday,
  Sunday;
}
*/

// 2.

enum class DayOfTheWeek(val isWeekend: Boolean = false) {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday(true),
    Sunday(true);

    companion object {
        fun today(): DayOfTheWeek {

            val calendarDAyOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            println(calendarDAyOfWeek)
            // we subtract 2 because in java calendar first day is Sunday, so we must subtract 1
            // and starts at index 1 not 0 so we need to subtract 1 again
            var adjustedDay = calendarDAyOfWeek - 2

            val days = DayOfTheWeek.values()
            if (adjustedDay < 0) {
                adjustedDay += days.count()
            }

            val today = days.first {
                it.ordinal == adjustedDay
            }

            return today

        }

        fun daysUntil(other: DayOfTheWeek): Int {

            val today = today()

            return if (other == today) {
                0
            } else {
                return if (other.ordinal > today.ordinal) {
                    other.ordinal - today.ordinal
                } else {
                    values().size - today.ordinal + other.ordinal
                }
            }
        }

        fun daysUntilNextWeekend(): Int {

            val weekEndFirstDay = values()[4]
            val today = today()

            return if (weekEndFirstDay == today) {
                0
            } else {
                return if (weekEndFirstDay.ordinal > today.ordinal) {
                    weekEndFirstDay.ordinal - today.ordinal
                } else {
                    values().size - today.ordinal + weekEndFirstDay.ordinal
                }
            }
        }

        fun getDayByIndex(index: Int): DayOfTheWeek? {
            return try {
                values()[index]
            } catch (ex: Exception) {
                null
            }
        }


        fun getDayByName(name: String): DayOfTheWeek? {
            return try {
                return valueOf(name)
            } catch (ex: Exception) {
                null
            }
        }

    }
}

// 5.

sealed class AcceptedCurrency {

    abstract val valueInDolars: Float
    var amount: Float = 0.0f

    companion object{
        // challenge 3
        fun sumCurrencies(currency1: AcceptedCurrency, currency2: AcceptedCurrency): String {
            return if (currency1.name == currency2.name) {
                "Total of ${currency1.amount} ${currency1.name} + ${currency2.amount} ${currency2.name} = " +
                        "${currency1.amount + currency2.amount} ${currency1.name}"
            } else {
                "Total of ${currency1.amount} ${currency1.name} + ${currency2.amount} ${currency2.name} = " +
                        "${currency1.totalValue() + currency2.totalValue()} ${Dollar().name}"
            }
        }

        fun enoughFunds( list: List<AcceptedCurrency>, totalToPayInDollars: Float): Boolean{
            println("Total to pay : $$totalToPayInDollars")
            println("Funds: \n ${ list.map { "${it.name} ${it.totalValue()}" }.joinToString("\n")}")

            val total = list.map { it.totalValue() }.sum()
            println("Total available : $$totalToPayInDollars")

            return if(total >= totalToPayInDollars){
                println("Sufficient funds")
                true
            } else {
                println("Insufficient funds")
                false
            }

        }
    }

    class Dollar : AcceptedCurrency() {
        override val valueInDolars: Float
            get() = 1.0f
    }

    class Euro : AcceptedCurrency() {
        override val valueInDolars: Float
            get() = 1.25f
    }

    class Crypto : AcceptedCurrency() {
        override val valueInDolars: Float
            get() = 2534.92f
    }

    val name: String
        get() = when (this) {
            is Euro -> "Euro"
            is Dollar -> "Dollar"
            is Crypto -> "Crypto"
            else -> ""
        }

    fun totalValue(): Float {
        return valueInDolars * amount
    }



}