import DocGen.Generator
import DocGen.HTML
import DocGen.Parser
import java.io.File
import java.util.ArrayList
import kotlin.system.exitProcess

/**
 * Klasa parsowania komentarzy
 * Parametry to ścieżki do katalogów
 * @args parametry do programu
 */
//https://github.com/HugoMatilla/KotlinCheatSheet

// --------------------------------------------------------------------------------------------

fun main(args: Array<String>) {

// Java   // public static ArrayList<Person> list = new ArrayList<Person>();
// Kotlin // val list = ArrayList<Person>()

    val list = getValidPathList(args)
    var g: Generator = Generator(ArrayList(),"")
    g.ini()

    list.add("src/DocGen/Parser.kt")
//    list.add("src/DocGen/World.kt")
    list.add("src/DocGen/Generator.kt")

    println("Files to generate documentation:")
    var i:Int = 0
    for( path in list ) {
        val p: Parser = Parser(path)
        g = Generator(p.comment, path)
        g.make(i)
    }
}

// --------------------------------------------------------------------------------------------

fun getValidPathList(args: Array<String>): ArrayList<String>
{
    if (args.isEmpty()) {
        println("Program args: Array<String> expected, exitProcess(1)")
        exitProcess(1)
    }

    val list = ArrayList<String>()

    for (i in args.indices) {
        val fileName = args[i]
        val file = File(fileName)
        val fileExists = file.exists()

        if(fileExists) {
//            println("$fileName does exist.")
            list.add(args[i])
        } else {
//            println("$fileName does not exist.")
        }
    }
    return list
}

fun optional()
{
//    var mes = File(filename).writeText(result.toString())
//    println("Message: " + mes);
//    File("somefile.txt").writeText(x.entries.joinToString("\n"))

//    File("data.txt").writeBytes("ABCD".toByteArray());

//    val stream = File("data.txt").inputStream()
//    val bytes = ByteArray(16)
//    stream.read(bytes)
//    stream.close()
//    println(bytes)
}