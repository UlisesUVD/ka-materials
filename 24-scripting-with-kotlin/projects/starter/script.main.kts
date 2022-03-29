import java.io.File

if(args.isEmpty()){
    println("[No args]")
} else {
    println("Args:\n${args.joinToString("\n")}")
}
println("Hello Scripting")


val folderPrefix = "folder="
val folderValue = valueFromArgsForPrefix(folderPrefix)

val hiddenPrefix = "hidden="
val hiddenValue = valueFromArgsForPrefix(hiddenPrefix).toBoolean()
println("hidden files? $hiddenValue")


if(folderValue != null){
    val folder = File(folderValue).absoluteFile
    folder.printFolderInfo()
} else {
    println("No path provided, printing working directory info")
    currentFolder().printFolderInfo()
}


fun currentFolder(): File {
    return File("").absoluteFile
}

fun File.contents() : List<File>{
    return this.listFiles().toList()
}

fun File.fileNames() : List<String>{
    return this.files().map { it.name }
}
fun File.folderNames() : List<String>{
    return this.folders().map { it.name }
}

fun File.folders(): List<File>{
    return this.contents().filter { it.isDirectory }.filter { if(hiddenValue){ true } else {!it.name.startsWith(".")} }
}

fun File.files(): List<File>{
    return this.contents().filter { it.isFile }.filter { if(hiddenValue){ true } else {!it.name.startsWith(".")} }
}

fun File.printFolderInfo(){

    println("Contents of: ${this.name}")

    if(this.folders().isNotEmpty()){
        println("- Folders:\n\t${this.folderNames().joinToString("\n\t")}")
    }

    if(this.files().isNotEmpty()){
        println("- Files:\n\t${this.fileNames().joinToString("\n\t")}")
    }

    println("Parent: ${this.parentFile.name}")
}

fun valueFromArgsForPrefix(prefix: String): String?{
    val arg = args.firstOrNull{ it.startsWith(prefix)}

    if (arg == null) return null

    val pieces = arg.split("=")
    return if(pieces.size == 2){
        pieces[1]
    } else {
        null
    }
}