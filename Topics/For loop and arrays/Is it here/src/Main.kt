fun main() {
    val size = readln().toInt()
    val array = IntArray(size)
    for (i in 0..array.lastIndex) {
        array[i] = readln().toInt()
    }
    val m = readln().toInt()
    if (array.contains(m)) {
        println("YES")
    } else {
        println("NO")
    }
}