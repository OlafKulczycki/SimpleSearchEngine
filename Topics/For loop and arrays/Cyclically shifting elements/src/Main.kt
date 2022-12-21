fun main() {
    val size = readln().toInt()
    val array = IntArray(size)
    for (i in 1 until size) {
        array[i] = readln().toInt()
    }
    array[0] = readln().toInt()
    for (i in 0..array.lastIndex) {
        print("${array[i]} ")
    }
}