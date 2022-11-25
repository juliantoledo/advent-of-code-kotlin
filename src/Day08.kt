fun main() {
    fun part1(input: List<String>): Int {
        var signals = input.map{ it.split(" | ").last().split(" ") }
        var total = 0
        for ( digit in signals.flatten() )
            when (digit.length) {
                2 -> total++
                3 -> total++
                4 -> total++
                7 -> total++
            }
        return total
    }

    fun remove(original: String, toRemove:String) : String {
        var result = ""
        original.forEach { char ->
            if (!toRemove.contains(char)) {
                result = result.plus(char)
            }
        }
        return result
    }

    fun compare(one: String, two:String) : Boolean {
        one.forEach { char ->
            if (!two.contains(char)) {
                return false
            }
        }
        return one.length == two.length
    }

    fun union(one: String, two:String) : String {
        var result = two
        one.forEach { char ->
            if (!result.contains(char)) {
                result = result.plus(char)
            }
        }
        return result
    }

    fun decode(signal : MutableList<String>): Array<String> {
        var patterns = Array<String>(10) { "" }
        //println(signals.toTypedArray().contentToString())
        for (item in signal) {
            when (item.length) {
                2 -> patterns[1] = item
                3 -> patterns[7] = item
                4 -> patterns[4] = item
                7 -> patterns[8] = item
            }
        }
        signal.removeIf{ it == patterns[1] }
        signal.removeIf{ it == patterns[7] }
        signal.removeIf{ it == patterns[4] }
        signal.removeIf{ it == patterns[8] }

        // 7 -1 = top
        var top = remove(patterns[7], patterns[1])

        var union74 = union(patterns[7], patterns[4])

        var bottom  = signal.map { digit  -> remove(digit, union74) }.first { it.length ==1 }

        patterns[9] = union(union74, bottom)
        signal.removeIf{ compare(it,patterns[9]) }

        var bottom7 = union(patterns[7], bottom)
        // find 3 removing bottom+7
        var mid  = signal.map { digit -> remove(digit, bottom7) }.first { it.length ==1 }

        patterns[3] = union(bottom7, mid)
        signal.removeIf{ compare(it,patterns[3]) }

        patterns[0] = remove(patterns[8], mid)
        signal.removeIf{ compare(it,patterns[0]) }

        patterns[6] = signal.first{ it.length == 6}
        signal.removeIf{ compare(it,patterns[6]) }

        patterns[2]  = signal.first { digit  -> remove(digit, patterns[9]).length == 1 }
        signal.removeIf{ compare(it,patterns[2]) }

        patterns[5] = signal.first()

        return patterns
    }

    fun part2(input: List<String>): Int {
        var signals = input.map{ it.split(" | ").first().split(" ").toMutableList() }
        var digits = input.map{ it.split(" | ").last().split(" ") }.toList()

        var total = 0
        for ((index, signal) in signals.withIndex()) {
            var patterns = decode(signal)
            var values = ""
            for (digit in digits[index] ) {
                val temp = patterns.withIndex().filter { it -> compare(it.value, digit) }.first()
                values += temp.index
            }
            total += values.toInt();
            println(values.toInt())
        }
        return total
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)

    val input = readInput("Day08")
    println(part1(input))

    // 2
    check(part2(testInput) == 61229)
    println(part2(input))

    //println(part2(input))
    //check(part2(testInput) == 168)
    //println(part2(input))

}
