fun main() {
    val size = readln().toInt()
    val array = IntArray(size)
    var count = 0
    for (i in 0..array.lastIndex) {
        array[i] = readln().toInt()
    }
    val target = readln().toInt()
    for (i in array.lastIndex downTo 0) {
        if (array[i] == target) {
            count++
        }
    }
    print(count)
}