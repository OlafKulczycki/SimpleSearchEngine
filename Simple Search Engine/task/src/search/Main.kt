package search

import java.io.File
import kotlin.system.exitProcess

const val matchingPeople = "\nEnter a name or email to search all matching people."
const val noMachingPeople = "No matching people found.\n"

data class Person (var firstname: String, var lastName: String, var eMail: String? = null)
enum class MatchingStrategy {
    ALL,
    ANY,
    NONE
}

fun readFile(args: Array<String>): MutableList<Person> {
    val peopleList = mutableListOf<Person>()
    val path = "C:\\Users\\OlafKulczycki\\GitRepos\\Simple Search Engine\\Simple Search Engine\\task\\src\\search\\"
    val inputFile = File(path + args[1])
    inputFile.forEachLine {
        val input = it.split(" ")
        if (input.size == 3) {
            peopleList.add(Person(input[0], input[1], input[2]))
        } else {
            peopleList.add(Person(input[0], input[1]))
        }
    }
    return peopleList
}
fun getPeople(): MutableList<Person> {
    println("Enter the number of people:")
    val size = readln().toInt()
    println("Enter all people:")
    val peopleList = mutableListOf<Person>()
    for (i in 0 until size) {
        val peopleInf = readln().split(" ")
        peopleList.add(i, Person(peopleInf[0], peopleInf[1]))
        if (peopleInf.size == 3) {
            peopleList[i].eMail = peopleInf[2]
        }
    }
    return peopleList
}
fun invertedIndex(updatedPeopleList: MutableList<Person>): MutableMap<String, MutableList<Int>> {
    val invertedIndex = mutableMapOf<String, MutableList<Int>>()
    updatedPeopleList.forEachIndexed { index, person ->
        invertedIndex.getOrPut(person.firstname.lowercase()) { mutableListOf<Int>() }.add(index)
        invertedIndex.getOrPut(person.lastName.lowercase()) { mutableListOf<Int>() }.add(index)
        person.eMail?.also { mail ->
            invertedIndex.getOrPut(mail.lowercase()) { mutableListOf<Int>() }.add(index)
        }
    }
    return invertedIndex
}
fun printResultSet(allSet: MutableSet<Int>, peopleList: MutableList<Person>) {
    if (allSet.size == 0) println(noMachingPeople)
    println("${allSet.size} persons found:")
    allSet.forEach { index ->
        val person = peopleList[index]
        if (person.eMail == null) {
            println("${person.firstname} ${person.lastName}")
        } else {
            println("${person.firstname} ${person.lastName} ${person.eMail}")
        }
    }
    println("")
}
fun printAllPeople(peopleList: MutableList<Person>) {
    for (i in 0 until peopleList.size) {
        if (peopleList[i].eMail == null) {
            println(peopleList[i].firstname + " " + peopleList[i].lastName)
        } else {
            println(peopleList[i].firstname + " " + peopleList[i].lastName + " " + peopleList[i].eMail)
        }
    }
    println("")
}
fun createSet (search: List<String>, invertedIndex: MutableMap<String, MutableList<Int>>): MutableSet<Int> {
    val allSet = mutableSetOf<Int>()
    when (search.size) {
        1 -> allSet.addAll(invertedIndex.getOrDefault(search[0], emptySet()))
        2 -> allSet.addAll(invertedIndex.getOrDefault(search[0], emptySet()).union(invertedIndex
            .getOrDefault(search[1], emptySet()).toSet()))
        3 -> allSet.addAll(invertedIndex.getOrDefault(search[0], emptySet()).union(invertedIndex
            .getOrDefault(search[1], emptySet()).union(invertedIndex.getOrDefault(search[2], emptySet()).toSet())))
    }
    return allSet
}
//todo die drei unteren funktionen zusammenfügen und enum einbinden.
fun findAll(invertedIndex: MutableMap<String, MutableList<Int>>, peopleList: MutableList<Person>) {
    val allSet = mutableSetOf<Int>()
    println(matchingPeople)
    val search = readln().lowercase().split(" ")
    try {
        when (search.size) {
            1 -> allSet.addAll(invertedIndex.getValue(search[0]))
            2 -> allSet.addAll(invertedIndex.getValue(search[0]).intersect(invertedIndex.getValue(search[1]).toSet()))
            3 -> allSet.addAll(invertedIndex.getValue(search[0]).intersect(invertedIndex.getValue(search[1])
                .intersect(invertedIndex.getValue(search[2]).toSet())))
        }
        printResultSet(allSet, peopleList)
    } catch (e: java.util.NoSuchElementException) {
        println(noMachingPeople)
    }
}
fun findAny(invertedIndex: MutableMap<String, MutableList<Int>>, peopleList: MutableList<Person>) {
    println(matchingPeople)
    val search = readln().lowercase().split(" ")
    try {
        val allSet = createSet(search, invertedIndex)
        println(allSet)
        printResultSet(allSet, peopleList)
        println("")
    } catch (e: java.util.NoSuchElementException) {
        e.printStackTrace()
        println(noMachingPeople)
    }
}
fun findNone(invertedIndex: MutableMap<String, MutableList<Int>>, peopleList: MutableList<Person>) {
    println(matchingPeople)
    val search = readln().lowercase().split(" ")
    try {
        val blackSet = createSet(search, invertedIndex)
        if (blackSet.size == 0) println(noMachingPeople)
        for (i in 0 until peopleList.size) {
            if (blackSet.contains(i)) {
                continue
            } else if (peopleList[i].eMail == null) {
                println(peopleList[i].firstname + " " + peopleList[i].lastName)
            } else {
                println(peopleList[i].firstname + " " + peopleList[i].lastName + " " + peopleList[i].eMail)
            }
        }
        println("")
    } catch (e: java.util.NoSuchElementException) {
        println(noMachingPeople)
    }
}
fun userMenu(invertedIndex: MutableMap<String, MutableList<Int>>, peopleList: MutableList<Person>) {
     do {
         println("=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit")
         val input = readln().toInt()
         when (input) {
             1 -> {
                 println("\nSelect a matching strategy: ALL, ANY, NONE")
                 when (readln().uppercase()) {
                     "ALL" -> findAll(invertedIndex, peopleList)
                     "ANY" -> findAny(invertedIndex, peopleList)
                     "NONE" -> findNone(invertedIndex, peopleList)
                     else -> println("Wrong Input, please repeat.")
                 }
             }
             2 -> printAllPeople(peopleList)
             0 -> continue
             else -> println("\nIncorrect option! Try again.")
         }
    } while (input != 0)
    println("\nBye!")
    exitProcess(0)
}

fun main(args: Array<String>) {
    val peopleList = if (args[0] == "--data") readFile(args) else getPeople()
    val invIndex = invertedIndex(peopleList)
    userMenu(invIndex, peopleList)
}
//166 vor Flos tipp.