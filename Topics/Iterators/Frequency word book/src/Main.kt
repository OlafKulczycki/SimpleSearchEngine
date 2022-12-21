fun main() {
    val list = readln().split(" ")
    val groupedList = list.groupingBy { it }.eachCount()
    groupedList.forEach { (key, count) -> println("$key $count")}
}