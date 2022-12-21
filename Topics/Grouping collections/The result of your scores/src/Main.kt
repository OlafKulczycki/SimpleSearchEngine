fun main() {
    val list = readln().split(" ").map { it.toDouble() }
    var passCount = 0
    var failCount = 0
    for (i in 0..list.lastIndex) if (list[i] >= 5) passCount++ else failCount++
    val res = "{pass=$passCount, fail=$failCount}"
    println(res)
}