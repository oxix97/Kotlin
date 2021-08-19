package Property

import kotlin.properties.Delegates

fun main() {
    var max: Int by Delegates.vetoable(0) { prop, old, new ->
        println("$old -> $new")
        new > old

    }
    max = 10
    max = 5     //기존값이 새 값보다 커서 false -> 할당 하지 않음
    max = 12
}