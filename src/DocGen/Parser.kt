package DocGen

import java.io.File
import kotlin.system.exitProcess

/**
 * Klasa parsuje komentarze
 * @Prarser szuka funkcji w tym pliku
 */

class Parser : CommentInterface {

    val fileName:String
    override var comment = ArrayList<Comment>()
//        get() = ArrayList<Comment>()

    val commStart: Regex = "\\s*\\/\\*\\*.*".toRegex()
    val commEnd: Regex   = "\\s*\\*\\/.*".toRegex()
    val dotPrefix: Regex = """\s*\*\s*""".toRegex()
    val funName: Regex   = """\s*.*\(.*\).*""".toRegex()
    var isComment: Boolean = false

    constructor(fileName: String) {
        this.fileName = fileName
        parseComments()
        showComments()
    }

    fun parseComments()
    {
        val lines: List<String> = File(fileName).readLines()

        val iterator = lines.iterator()

        comment.add( Comment() )   // init empty comment

        if ( comment.isEmpty() )
        {
            println("Brak komentarza, kończę program (bład inicjalizacji)")
            exitProcess(2);
        }

        iterator.forEach {
            //            println("The element is $it")
            if( it.matches(commEnd) ) {
                isComment = false
                var a:String
                while ( iterator.hasNext() ) {
                    a = iterator.next()
//                    println("nie " + a)
                    if ( a.matches(funName) ) {
                        comment[comment.lastIndex].fun_line = a;
//                        println(comment[comment.lastIndex].fun_line)
                        break;
                    }
                }
                comment.add( Comment() ) // new empty Comment object
//                println("Następny komentarz nr: " + comment.lastIndex)
            }
            if ( isComment ) {
//                println(it)
                comment[comment.lastIndex].lines.add(it.replace(dotPrefix," "))
            }
            if( it.matches(commStart) )
                isComment = true
        }
        if (comment[comment.lastIndex].fun_line.isEmpty())
            comment.removeAt(comment.lastIndex);
    }

    private fun findFuncName( i: Iterator<String> ): String {
        if (i.hasNext())
        {
            i.next();
//            if(i)
        }
        return i.toString()
    }

    fun showComments()
    {
        for( c in comment)
        {
            println(c.fun_line)
            for (line in c.lines)
                println(line)
        }
    }
}