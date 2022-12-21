import kotlin.system.exitProcess

/* Do not change code below */
fun main() {
    val list = readln().split(" ")
    var isPalindrome: (String) -> Boolean = { x -> x.reversed() == x }
    var res = false
    for (i in list.indices) {
        res = isPalindrome(list[i])
        if (!res) {
            println(false)
            exitProcess(0)
        }
    }
    println(res)
}