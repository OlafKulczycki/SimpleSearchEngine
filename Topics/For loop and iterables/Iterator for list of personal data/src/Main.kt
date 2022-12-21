class ListStorageIterator(private val workTeam: ListStorage) : Iterator<StoredNode> {

    lateinit var current: StoredNode
    private var isAccessed: Boolean = false

    override fun hasNext(): Boolean {
        // put your code here
    }

    override fun next(): StoredNode {
        // put your code here
    }
}

fun main() {
    // don't change this code
    val listStorage = ListStorage(StoredNode("Nik", 30))
    listStorage.add(StoredNode("Sofi", 25))
    listStorage.add(StoredNode("Mike", 45))
    val iterStorage = listStorage.iterator()
    while (iterStorage.hasNext()){
        println(iterStorage.next().toNodeString())
    }
}