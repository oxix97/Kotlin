package Property

import kotlin.properties.Delegates

class User{
    var name: String by Delegates.observable("NONAME") {
        prop,old,new->
        println("$old -> $new")
    }
    //useEffect 비슷한 개념?
}

fun main() {
    val user = User()
    user.name = "Kildong"
    user.name = "Dooly"
}