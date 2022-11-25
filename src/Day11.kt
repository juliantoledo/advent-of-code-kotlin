fun main() {
    fun step(matrix: List<Array<Int>>, x: Int, y: Int, flashed: List<Array<Boolean>>, internal: Boolean = true) {
        if (!flashed[x][y]){
            if (internal) matrix[x][y]++
            if (matrix[x][y] > 9) {
                flashed[x][y] = true
                matrix[x][y] = 0

                // left
                if (y > 0) {
                    step(matrix, x, y-1, flashed)
                }
                // right
                if (y < matrix[x].lastIndex) {
                    step(matrix, x, y+1, flashed)
                }
                // up
                if (x > 0)  {
                    step(matrix, x-1, y, flashed)
                }
                // down
                if (x < matrix.lastIndex)  {
                    step(matrix, x+1, y, flashed)
                }

                // up-left
                if (y > 0 && x > 0) {
                    step(matrix, x-1, y-1, flashed)
                }
                // down-right
                if (y < matrix[x].lastIndex && x < matrix.lastIndex) {
                    step(matrix, x+1, y+1, flashed)
                }
                // up-right
                if (y < matrix[x].lastIndex && x > 0) {
                    step(matrix, x-1, y+1, flashed)
                }
                // down-left
                if (y > 0 && x < matrix.lastIndex) {
                    step(matrix, x+1, y-1, flashed)
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        var matrix = mutableListOf<Array<Int>>()
        for( i in input) {
            val row = i.map { it.digitToInt() }.toTypedArray()
            matrix.add(row)
        }

        var total = 0
        for ( i in 1..1000) {
            matrix = matrix.map { it.map { r -> r + 1 }.toTypedArray() }.toMutableList()
            var flashed = matrix.map { it.map { false }.toTypedArray() }.toMutableList()
            for ((x,row) in matrix.withIndex()) {
                for ((y,cell) in row.withIndex()) {
                    step(matrix, x, y, flashed, false)
                }
            }
            total += flashed.toTypedArray().flatten().filter { it }.count()
            println("flashes ($i): " + matrix.toTypedArray().flatten().filter { it == 0 }.count())
        }
        /*
        for ((x,row) in matrix.withIndex()) {
            for ((y, cell) in row.withIndex()) {
                print(cell)
            }
            println()
        }
        */
        return total
    }

    fun step2(matrix: List<Array<Int>>, x: Int, y: Int, flashed: List<Array<Boolean>>, internal: Boolean = true) {
        if (internal) matrix[x][y]++

        if (!flashed[x][y]){
            if ( matrix[x][y] > 9) {
                flashed[x][y] = true
                matrix[x][y] = 0

                // left
                if (y > 0 && !(flashed[x][y-1])) {
                    step(matrix, x, y-1, flashed)
                }
                // right
                if (y < matrix[x].lastIndex && !(flashed[x][y+1])) {
                    step(matrix, x, y+1, flashed)
                }
                // up
                if (x > 0 && !(flashed[x-1][y]))  {
                    step(matrix, x-1, y, flashed)
                }
                // down
                if (x < matrix.lastIndex && !(flashed[x+1][y]))  {
                    step(matrix, x+1, y, flashed)
                }

                // up-left
                if (y > 0 && x > 0 && !(flashed[x-1][y-1])) {
                    step(matrix, x-1, y-1, flashed)
                }
                // down-right
                if (y < matrix[x].lastIndex && x < matrix.lastIndex && !(flashed[x+1][y+1])) {
                    step(matrix, x+1, y+1, flashed)
                }
                // up-right
                if (y < matrix[x].lastIndex && x > 0 && !(flashed[x-1][y+1])) {
                    step(matrix, x-1, y+1, flashed)
                }
                // down-left
                if (y > 0 && x < matrix.lastIndex && !(flashed[x+1][y-1])) {
                    step(matrix, x+1, y-1, flashed)
                }
            }
        }
    }


    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day11_test")
    //println(part1(testInput))
    //check(part1(testInput) == 1656)

    val test = readInput("Day11")
    println(part1(test))
}
