fun solution(first: Set<String>, second: MutableList<String>): Boolean {
    var check = true
    second.forEach {
        if (!first.contains(it)) {
            check = false
        }
    }
    return check
}

val arr = arrayOf(1,2,3,4,5)
fun main() {
    for (element in arr)
        println(element)
    for (index in arr)
        println(index)
    for (index in arr.indices)
        println(index)
    for (index in arr.lastIndex downTo 0)
        println(index)
}