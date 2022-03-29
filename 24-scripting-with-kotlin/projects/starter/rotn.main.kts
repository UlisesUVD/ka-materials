

var word = "Hello world"
var factor = 1



if(args.isEmpty()){
    println("[No args]")
    println("Default example")
    println("Rotating: $word")
    println("By a factor: $factor")
    println(rotate())

} else {

    word = args[0]
    factor = 1

    if(args.size > 1){
        factor = args[1].toInt()
    }

    println("Rotate: $word")
    println("By a factor: $factor")
    println(rotate())

}

fun rotate() : String{

    val builder = StringBuilder()

    for( i in 0 until word.length){

        when(val code = word[i].code){
            in 97..122 ->{
                var newCode = code + factor
                if(newCode > 122) {
                    newCode %= 96
                    newCode += 96
                }
                builder.append(newCode.toChar())
            }
            in 65..90 ->{
                var newCode = code + factor
                if(newCode > 90){
                    newCode %= 65
                    newCode += 65
                }
                builder.append(newCode.toChar())
            }

            else -> {
                builder.append(code.toChar())
            }
        }

    }

    return builder.toString()
}