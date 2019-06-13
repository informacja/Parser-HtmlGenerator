package DocGen

import DocGen.CommentInterface as CommentInterface1


class Comment   {
//    override var fun_line: String = ""
//        get() = field
//        set(value) { field = value }
//    override val lines: ArrayList<String> = ArrayList<String>()
//        get() = field
//    var fun_line: String = ""
//    val lines = ArrayList<String>()

    val lines = ArrayList<String>()
//        get() = lines
//        set(v){lines = v}
    var fun_line:String = ""
        get() = field
        set(value) { field = value }

    constructor()
}