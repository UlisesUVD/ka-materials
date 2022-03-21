import kotlin.math.PI
import kotlin.math.pow

fun main(){

    val square = Square(2.0)
    val triangle = Triangle(2.0, 4.0)
    val circle = Circle(5.0)


    val shapes = arrayOf<Shape>(square, triangle, circle)

    val areas = shapes.map { (it as Area).area }
    println(areas.joinToString(","))
}

interface Area{
    val area:Double
}

open class Shape

class Square(var side: Double): Shape(), Area{
    override val area: Double
        get() = side * side

}

class Triangle(var base: Double, var height: Double): Shape(), Area{
    override val area: Double
        get() = (base * height) / 2
}

class Circle(var radius: Double): Shape(), Area{
    override val area: Double
        get() = PI * radius.pow(2)
}