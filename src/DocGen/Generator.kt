package DocGen

import DocGen.CommentInterface as CommentInterface1
import DocGen.HtmlBuilder
import java.io.File

/**
 * Material Design for Bootstrap 4
 * Version: MDB FREE: 4.8.2
 *
 *
 * Copyright: Material Design for Bootstrap
 * https://mdbootstrap.com/
 *
 * Read the license: https://mdbootstrap.com/general/license/
 *
 *
 * Documentation: https://mdbootstrap.com/
 *
 * Getting started: https://mdbootstrap.com/docs/jquery/getting-started/download/
 *
 * Tutorials: https://mdbootstrap.com/education/bootstrap/
 *
 * Templates: https://mdbootstrap.com/templates/
 *
 * Support: https://mdbootstrap.com/forums/forum/support/
 *
 * Contact: office@mdbootstrap.com
 *
 * @Attribution: Animate CSS, Twitter Bootstrap, Materialize CSS, Normalize CSS, Waves JS, WOW JS, Toastr, Chart.jss
 *
 */

class Generator(override var comment: ArrayList<Comment>, var path:String) : CommentInterface1 {

    val a: HtmlBuilder = HtmlBuilder()
    var html: HTML = HTML()
    val fileIntro = "intro.html"
    val fileDoc = "doc.html"
    var doc = File(fileDoc)

    fun make( i:Int ) {
        println( this.comment[0].lines.toString())
//        this.comment.add( comment);
//        println("Rozm " + comment.lines.size)
        val result: HTML = gen(this.comment[i], path)
        println(result)

        doc.appendText(result.toString())
    }

    private fun gen(comment: Comment, path: String): HTML {
        return a.generateDoc(comment, path)
    }


    fun ini()
    {
        this.comment = comment;
        doc.writeText("")
        var me = File(fileIntro).writeText( a.generateIntro().toString())


    }
//    constructor()  {
//        ini()
//    }
//    generateHtml()

}

