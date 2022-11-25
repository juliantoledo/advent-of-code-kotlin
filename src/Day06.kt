import java.nio.ByteBuffer

class Lanterfish (age: Int) {
    var currentAge = age;
    fun baby() : Lanterfish {
        return Lanterfish(8);
    }
    fun day() : Lanterfish? {
        currentAge--
        if(currentAge == -1) {
            currentAge = 6
            return baby()
        }
        return null
    }
}

fun main() {
    fun part1(input: List<String>, days: Int): Int {
        var school = ArrayList<Lanterfish>()
        var index = 0
        for (i in input[0].split(",")) {
            school.add(Lanterfish(i.toInt()))
        }
        for (x in 1 .. days) {
            var newSchool = ArrayList<Lanterfish>()
            for (fish in school) {
                val newFish = fish.day()
                if (newFish != null) {
                    newSchool.add(newFish)
                }
            }
            school.addAll(newSchool);
            println("$x ")
        }
        return school.size
    }

    fun part2(input: List<String>, days: Int): Long {
        var ages = Array<Long>(9) { 0 }
        for (i in input[0].split(",")) {
            ages[i.toInt()]++
        }
        for (x in 1 .. days) {
            val newAges = Array<Long>(9) { 0 }
            newAges[6] = ages[0]
            newAges[8] = ages[0]
            for (i in 1..8) {
                newAges[i - 1] += ages[i]
            }
            ages = newAges
            println("$x ")
        }
        val temp = ages.sum()
        return temp
    }

    // 1
    val testInput = readInput("Day06_test")
    check(part1(testInput, 18) == 26)
    check(part2(testInput, 80) == 5934L)
    check(part2(testInput, 256) == 26984457539)

    val input = readInput("Day06")
    println(part2(input, 80))
    println(part2(input, 256))
    /*
    println("*****")
    check(part2(testInput) == 12)
    println(part2(input))
    */
}
