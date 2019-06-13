package DocGen

class HtmlBuilder {

    fun generateDoc(comment:Comment, path:String): HTML
    {
        val geHtml =
                html {
                    body {
                        h3 { +"<hr><span class='mt-5'>Plik: </span>$path " }

                            val a: String = comment.fun_line;
                            p{ +"<span class='class='font-weight-light'>Funkcja:</span> <code class='text-monospace'> $a </code>" }

                            p {
                                +"<blockquote class='blockquote'>"
                                for (line in comment.lines) {
                                    if (line.trim().isNotEmpty()) {
                                        if (line.matches(".*@+.*".toRegex()))
//                                            +"<span class='class='font-weight-light'></span> $line"
                                            +"<footer class='blockquote-footer mb-3'>$line</footer>"
                                        else
                                            +"<tt class='mb-0'>$line</tt><br>"
                                    }
                                }
                                +"</blockquote>"
                            }
                    }
                }
        return geHtml
    }

    fun generateIntro(): HTML
    {
        val geHtml =
                html {
                    body {
                        h1 { +"Dzień dobry" }
                        p { b { +"Treść tej strony została wygenerowana w "}
                            a(href = "http://jetbrains.com/kotlin") { +"Kotlie" }
                        }
                        p { +"Dokumentacja jest poniżej :)"}

                    }
                }
//        println(geHtml.javaClass.canonicalName)
        return geHtml
    }

    fun generateHtml(args: Array<String>): HTML {
        val geHtml =
                html {
                    body {
                        h1 { +"HTML encoding with Kotlin" }
                        p { +"this format can be used as an alternative markup to HTML" }

                        // an element with attributes and text content
                        a(href = "http://jetbrains.com/kotlin") { +"Kotlin" }

                        // mixed content
                        p {
                            +"This is some"
                            b { +"mixed" }
                            +"text. For more see the"
                            a(href = "http://jetbrains.com/kotlin") { +"Kotlin" }
                            +"project"
                        }
                        p { +"some text" }

                        // content generated from command-line arguments
                        p {
                            +"Command line arguments were:"
                            ul {
                                for (arg in args)
                                    li { +arg }
                            }
                        }
                    }
                }
        println(geHtml.javaClass.canonicalName)
        return geHtml
    }


    /**
    Poraz kolejny w pliku
    Polecam PW
     */

    fun html(init: HTML.() -> Unit): HTML {
        val html = HTML()
        html.init()
        return html
    }
}


interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String? {
        val builder = StringBuilder()
        for (a in attributes.keys) {
            builder.append(" $a=\"${attributes[a]}\"")
        }
        return builder.toString()
    }


    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class HTML() : TagWithText("html") {
//    fun head(init: Head.() -> Unit) = initTag(Head(), init)

    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head() : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title() : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun h3(init: H3.() -> Unit) = initTag(H3(), init)
    fun ul(init: UL.() -> Unit) = initTag(UL(), init)
//    fun hr(init: HR.() -> Unit) = initTag(HR(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body() : BodyTag("body")
class UL() : BodyTag("ul") {
    fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

class B() : BodyTag("b")
class LI() : BodyTag("li")
class P() : BodyTag("p")
class H1() : BodyTag("h1")
class H3() : BodyTag("h3")

class A() : BodyTag("a") {
    public var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}
