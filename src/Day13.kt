fun main() {

    fun foldLeft(dots: MutableList<Pair<Int, Int>>, x: Int) {
        var indexes = arrayListOf<Int>()
        dots.forEachIndexed{index, dot ->
            if (dot.first > x) {
                val dif = (dot.first - x) * 2
                val newPair = Pair(dot.first - dif, dot.second)
                if (dots.contains(newPair)) {
                    indexes.add(index)
                } else {
                    dots[index] = newPair
                }
            }
        }
        indexes.reversed().forEach { dots.removeAt(it) }
    }

    fun foldUp(dots: MutableList<Pair<Int, Int>>, y: Int) {
        var indexes = arrayListOf<Int>()
        dots.forEachIndexed{index, dot ->
            if (dot.second > y) {
                val dif = (dot.second - y) * 2
                val newPair = Pair(dot.first, dot.second - dif)
                if (dots.contains(newPair)) {
                    indexes.add(index)
                } else {
                    dots[index] = newPair
                }

            }
        }
        indexes.reversed().forEach { dots.removeAt(it) }
    }

    fun part1(input: List<String>): Int {
        var dots = mutableListOf<Pair<Int, Int>>()
        input.forEach { line ->
            line.splitTo(",") { a, b ->
                dots.add(Pair(a.toInt(), b.toInt()))
            }
        }
        println(dots)
        foldUp(dots, 7)
        foldLeft(dots, 5)
        println(dots)
        return dots.size
    }

    fun part2(input: List<String>): Int {
        var dots = mutableListOf<Pair<Int, Int>>()
        input.forEach { line ->
            line.splitTo(",") { a, b ->
                dots.add(Pair(a.toInt(), b.toInt()))
            }
        }
        foldLeft(dots, 655)
        println(dots.size)
        foldUp(dots, 447)
        foldLeft(dots, 327)
        foldUp(dots, 223)
        foldLeft(dots, 163)
        foldUp(dots, 111)
        foldLeft(dots, 81)
        foldUp(dots, 55)
        foldLeft(dots, 40)
        foldUp(dots, 27)
        foldUp(dots, 13)
        foldUp(dots, 6)
        println(dots)

        var x = 0
        var y = 0
        dots.forEach{dot ->
            if (dot.first > x) x = dot.first
            if (dot.second > y) y = dot.second
        }
        println("$x, $y")
        var words = Array<Array<String>>(y+1){Array<String>(x+1){" "}}
        dots.forEach{dot ->
            words[dot.second][dot.first]="#"
        }
        words.forEach { row ->
            println(row.contentToString())
        }
        return dots.size
    }

    val testInput = readInput("Day13_test")
    println(part1(testInput))
    //check(part1(testInput) == 1656)

    val test = readInput("Day13")
    println(part2(test))
}
