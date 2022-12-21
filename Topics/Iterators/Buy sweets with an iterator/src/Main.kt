fun countSum(sweets: Map<String, Int>): Int {
    return sweets.values.sum()
}
fun main() {
    var cart = mutableMapOf<String, Int>()
    val size = readln().toInt()
    for (i in 0 until size) {
        val input = readln().split(" ")
        cart[input[0]] = input[1].toInt()
    }
    println(countSum(cart)) // do not change this line
}