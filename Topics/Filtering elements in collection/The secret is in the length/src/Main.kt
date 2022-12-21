fun main() {
    val list = readln().split(" ")
    // write your code here
    val res = list.filter { it.length == 5 && it != list[0] }
    println(res)
}