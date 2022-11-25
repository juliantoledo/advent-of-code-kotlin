fun main() {
    fun part1(input: List<String>): Int {
        var map = Array<Array<Int>>(1000) {Array<Int>(1000){0} }
        for (i in input) {
            val temp = i.split(" -> ")
            val temp1 = temp[0].split(",")
            val temp2 =temp[1].split(",")
            var x1 = temp1[0].toInt()
            var x2 = temp2[0].toInt()
            var y1 = temp1[1].toInt()
            var y2 = temp2[1].toInt()
            if (x1==x2 || y1==y2) {
                if (x1 > x2) x2 = x1.apply{x1 = x2}
                if (y1 > y2) y2 = y1.apply{y1 = y2}
                for (x in x1..x2) {
                    for (y in y1..y2) {
                        map[x][y] += 1
                    }
                }
            }
        }
        return map.flatten().filter {( it >= 2)}.size
    }

    fun part2(input: List<String>): Int {
        var map = Array<Array<Int>>(1000) {Array<Int>(1000){0} }
        for (i in input) {
            val temp = i.split(" -> ")
            val temp1 = temp[0].split(",")
            val temp2 =temp[1].split(",")
            var x1 = temp1[0].toInt()
            var x2 = temp2[0].toInt()
            var y1 = temp1[1].toInt()
            var y2 = temp2[1].toInt()
            val absV = kotlin.math.abs(x1 - x2) - kotlin.math.abs(y1 - y2)
            if (absV == 0) {
                val range = if (x1 < x2) x1.rangeTo(x2) else x1.downTo(x2)
                val rangeY = if (y1 < y2) y1.rangeTo(y2) else y1.downTo(y2)
                for ((index, x) in range.withIndex()) {
                    map[x][rangeY.elementAt(index)] += 1
                }
            }
            if (x1 == x2 || y1 == y2) {
                println(i)
                val range = if (x1 < x2) x1.rangeTo(x2) else x1.downTo(x2)
                for (x in range) {
                    val rangeY = if (y1 < y2) y1.rangeTo(y2) else y1.downTo(y2)
                    for (y in rangeY) {
                        map[x][y] += 1
                    }
                }
            }
        }
        println(map.contentDeepToString())
        val result = map.flatten().filter {( it >= 2)}.size
        return result
    }

    // 1
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)

    val input = readInput("Day05")
    check(part1(input) == 5774)
    //println(part1(input))

    println("*****")

    check(part2(testInput) == 12)
    println(part2(input))

}
