/* Do not change code below */
fun main() {
    val list = readln().split(" ")
    for (i in 0..list.size) list[i].groupingBy {  }
    val res = list.groupingBy { it. }.eachCount()

    println(res)
}