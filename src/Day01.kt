fun main() {
    fun part1(input: List<String>): Int {
        var previousValue = input[0].toInt()
        var depthIncreased = 0
        for (i in input) {
            var depth = i.toInt();
            if (depth > previousValue)
                depthIncreased++
            previousValue = depth
        }
        return depthIncreased
    }

    fun part2(input: List<String>): Int {
        var previousValue = input[0].toInt()+input[1].toInt()+input[2].toInt()
        var depthIncreased = 0
        for (i in 3..input.size-3) {
            var depthSum = input[i].toInt()+input[i+1].toInt()+input[i+2].toInt()
            if (depthSum > previousValue)
                depthIncreased++
            previousValue = depthSum
        }
        return depthIncreased
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    val input = readInput("Day01")
    println(part1(input))

    val testInput2 = readInput("Day01_test")
    check(part2(testInput) == 5)
    val input2 = readInput("Day01")
    println(part2(input))
}
