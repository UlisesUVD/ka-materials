fun main() {

    // 1.
    val c = C()

    // 2.
    val castedA = c as A
    val a = A()
    val castedC = a as C // this will fail

    // 3.

}

// 1.

open class A {
    init {
        println("I'm ${this.javaClass.name}")
    }

}

open class B : A() {
    init {
        println("I'm ${this.javaClass.name}")
    }
}

class C : B() {
    init {
        println("I'm ${this.javaClass.name}")
    }
}

// 3.
class StudentBaseBallPlayer(firstName: String, lastName: String, position: String, number: Int, battingAverage: Int) :
    StudentAthlete(firstName, lastName) {


}

// 4.
sealed class Resource{
    class Success(data: String) : Resource()
    class Loading(data: String) : Resource()
    class Error(error: String) : Resource()
}