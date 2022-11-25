fun main() {
    fun isLowPoint(matrix: MutableList<Array<Int>>, x: Int, y: Int): Boolean {
        val value = matrix[x][y]
        //left
        if (y > 0 && value >= matrix[x][y-1] ) return false
        // right
        if (y < matrix[x].lastIndex && value >= matrix[x][y+1] ) return false
        // up
        if (x > 0 && value >= matrix[x-1][y] )  return false
        // down
        if (x < matrix.lastIndex && value >= matrix[x+1][y]) return false
        return true
    }

    fun findLowPoints(matrix: MutableList<Array<Int>>): List<Int> {
        var lowPoints = mutableListOf<Int>()
        for((x, row) in matrix.withIndex()) {
            for((y, value) in row.withIndex()) {
                if (isLowPoint(matrix, x, y)) lowPoints.add(value)
            }
        }
        return lowPoints
    }

    fun part1(input: List<String>): Int {
        var matrix = mutableListOf<Array<Int>>()
        for( i in input) {
            val row = i.map { it.digitToInt() }.toTypedArray()
            matrix.add(row)

        }
        println (matrix)
        return findLowPoints(matrix).map{ it+1 }.sum()
    }

    fun testNeighbours(matrix: List<Array<Int>>, x: Int, y: Int): Int {
        matrix[x][y] = 9
        //left
        var value = 1;
        if (y > 0 && matrix[x][y-1] < 9) {
            value += testNeighbours(matrix, x, y-1)
        }
        // right
        if (y < matrix[x].lastIndex && matrix[x][y+1] < 9) {
            value += testNeighbours(matrix, x, y+1)
        }
        // up
        if (x > 0 && matrix[x-1][y] < 9)  {
            value += testNeighbours(matrix, x-1, y)
        }
        // down
        if (x < matrix.lastIndex && matrix[x+1][y] < 9) {
            value += testNeighbours(matrix, x + 1, y)
        }
        return value;
    }

    fun part2(input: List<String>): Int {
        var matrix = mutableListOf<Array<Int>>()
        for( i in input) {
            val row = i.map { it.digitToInt() }.toMutableList<Int>().toTypedArray()
            matrix.add(row)
        }
        println (matrix)
        var basins = mutableListOf<Int>()
        for((x, row) in matrix.withIndex()) {
            for(y in row.indices) {
                if (isLowPoint(matrix, x, y)) {
                    basins.add(testNeighbours(matrix, x, y))
                }
            }
        }
        println(basins)
        basins.sortDescending()
        return basins[0] * basins[1] * basins[2]
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)

    val input = readInput("Day09")
    check(part1(input) == 502)

    check(part2(testInput) == 1134)

    check(part2(input) == 1330560)
}
