fun solution(setSource: Set<String>, listSource: MutableList<String>): MutableSet<String> {
    val mutSet = mutableSetOf<String>()
    mutSet.addAll(setSource)
    mutSet.addAll(listSource)
    return mutSet
}