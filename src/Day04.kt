
class Board(values: List<List<Int>>) {
    private val rows = values
    var rowsHit = MutableList<Int>(5) {0}
    var columnsHit = MutableList<Int>(5) {0}

    fun hit(drawn: Int ): Boolean {
        for (rowIndex in rows.indices) {
            for (columnIndex in rows[rowIndex].indices) {
                if (rows[rowIndex][columnIndex] == drawn) {
                    if (++rowsHit[rowIndex] == 5)
                        return true
                    if (++columnsHit[columnIndex] == 5)
                        return true
                }
            }
        }
        return false
    }

    fun score(list:List<Int>, last:Int): Int {
        var flatValues = rows.flatten() as MutableList
        for (d in list) {
            flatValues.remove(d);
            if (d == last) {
                return flatValues.sum()
            }
        }
        return flatValues.sum()
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val drawn = input[0].split(",").map { it.toInt() }
        println(drawn)
        val rawBoards = input.drop(1).filter { it.isNotEmpty() }.mapNotNull { s -> s.chunked(3).map { it.trim().toInt() }}.chunked(5)

        var boards = mutableSetOf<Board>()
        for (i in rawBoards) {
            boards.add(Board(i));
            val board = Board(i)
            println(i)
        }

        for (d in drawn) {
            for (board in boards) {
                if (board.hit(d)) {
                    val sum = board.score(drawn, d)
                    return sum*d
                };
            }
        }
        return 0;
    }

    fun part2(input: List<String>): Int {
        val drawn = input[0].split(",").map { it.toInt() }
        println(drawn)
        val rawBoards = input.drop(1).filter { it.isNotEmpty() }.mapNotNull { s -> s.chunked(3).map { it.trim().toInt() }}.chunked(5)

        var boards = mutableSetOf<Board>()
        for (i in rawBoards) {
            boards.add(Board(i));
            val board = Board(i)
            println(i)
        }

        var total = 0
        loop@ for (d in drawn) {
            var index = 0
            while (index < boards.size) {
                val board = boards.elementAt(index)
                if (board.hit(d)) {
                    val sum = board.score(drawn, d)
                    total = sum*d
                    boards.remove(board)
                } else {
                    index++
                }
            }
        }
        return total;
    }

    // 1
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)

    val input = readInput("Day04")
    println(part1(input))

    println("*****")

    // 2
    check(part2(testInput) == 1924)
    // too High 11712
    println(part2(input))
}
