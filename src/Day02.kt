fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0;
        var horizontal = 0;
        for (i in input) {
           val splits = i.split(" ")
            when (splits[0]) {
                "forward" -> horizontal += splits[1].toInt()
                "down" -> depth += splits[1].toInt()
                "up" -> depth -= splits[1].toInt()
                else -> {
                    println("$i is an invalid value")
                }
            }
        }
        return depth * horizontal;
    }

    fun part2(input: List<String>): Int {
        var depth = 0;
        var horizontal = 0;
        var aim = 0;
        for (i in input) {
            val splits = i.split(" ")
            when (splits[0]) {
                "forward" -> {
                    horizontal += splits[1].toInt()
                    depth += (splits[1].toInt()*aim)
                };
                "down" -> aim += splits[1].toInt()
                "up" -> aim -= splits[1].toInt()
                else -> {
                    println("$i is an invalid value")
                }
            }
        }
        return depth * horizontal;
    }

    // 1
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    val input = readInput("Day02")
    println(part1(input))

    // 2
    val testInput2 = readInput("Day02_test")
    check(part2(testInput) == 900)
    val input2 = readInput("Day02")
    println(part2(input))
}
