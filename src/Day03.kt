fun main() {
    fun part1(input: List<String>): Int {
        var gamma = ""
        var epsilon = ""
        var sumDigits = IntArray(input[0].length);

        for (i in input) {
            for (j in i.indices)
                sumDigits[j] += i[j].digitToInt()
        }
        println (sumDigits.contentToString())
        for (d in sumDigits.indices) {
            if (sumDigits[d].toInt() > input.size/2) {
                gamma += 1
                epsilon += 0
            } else {
                gamma += 0
                epsilon += 1
            }
        }
        println (gamma.toInt(2))
        println (epsilon.toInt(2))
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>): Int {
        var (oxy, c02) = input.partition { it.startsWith('0') }
        if (oxy.size < c02.size) c02 = oxy.apply{oxy = c02}

        for(i in 1 until oxy[0].length) {
            if (oxy.size > 1) {
                val (items0, items1) = oxy.partition { it[i].digitToInt() == 0 }
                oxy = if (items1.size >= items0.size) {
                    items1 as ArrayList<String>;
                } else {
                    items0 as ArrayList<String>;
                }
            }
        }
        for(i in 1 until c02[0].length) {
            if (c02.size > 1) {
                val (items0, items1) = c02.partition { it[i].digitToInt() == 0 }
                c02 = if (items0.size <= items1.size) {
                    items0 as ArrayList<String>;
                } else {
                    items1 as ArrayList<String>;
                }
            }
        }
        println("oxy " + oxy[0].toInt(2));
        println("c02 " + c02[0].toInt(2));
        return oxy[0].toInt(2) * c02[0].toInt(2)
    }

    // 1
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))

    println("*****")

    // 2
    check(part2(testInput) == 230)
    println(part2(input))
}
