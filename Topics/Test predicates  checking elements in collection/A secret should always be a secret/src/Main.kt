fun main() {
    val list = readln().lowercase().split(" ")
    var res: Boolean = !list.contains("secret")
    println(res)
}