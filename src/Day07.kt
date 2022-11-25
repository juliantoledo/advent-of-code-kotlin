fun main() {
    fun part1(input: List<String>): Int {
        var crabs = input[0].split(",").map { it.toInt() }
        println(crabs)
        crabs = crabs.sorted()
        var distances = mutableListOf<Int>()
        for (i in crabs.first()..crabs.last()) {
            var distance = 0
            crabs.forEach { distance+= kotlin.math.abs(i-it) }
            distances.add(distance.toInt())
        }
        return distances.minOrNull()!!
    }

    fun part2(input: List<String>): Int {
        var crabs = input[0].split(",").map { it.toInt() }
        println(crabs)
        crabs = crabs.sorted()
        var distances = mutableListOf<Int>()
        for (i in crabs.first()..crabs.last()) {
            var distance = 0
            crabs.forEach {
                val d = kotlin.math.abs(i-it)
                distance+= (d)*(d + 1) / 2
            }
            distances.add(distance.toInt())
        }
        return distances.minOrNull()!!
    }

    // 1
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)

    val input = readInput("Day07")
    println(part1(input))

    // 2
    check(part2(testInput) == 168)
    println(part2(input))

}
