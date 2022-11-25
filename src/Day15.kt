import java.lang.Math.abs
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

fun main() {

    data class RiskPair(
        val location: Pair<Int,Int>,
        val riskLevel: Int
    )

    class RiskPairComparator : Comparator<RiskPair> {
        override fun compare(first: RiskPair, second: RiskPair): Int {
            return first.riskLevel.compareTo(second.riskLevel)
        }
    }

    fun nextNodes(matrix: MutableList<IntArray>, node: Pair<Int,Int>): List<Pair<Int, Int>> {
        var nodes = arrayListOf<Pair<Int,Int>>()
        val x = node.first
        val y = node.second
        if (y > 0) nodes.add(Pair(x, y-1))
        if (y < matrix[x].lastIndex) nodes.add(Pair(x, y+1))
        if (x > 0) nodes.add(Pair(x-1, y))
        if (x < matrix.lastIndex) nodes.add(Pair(x+1, y))
        return nodes
    }

    fun dijkstra(start: Pair<Int, Int>, matrix: MutableList<IntArray>): Int {
        val priorityQueue = PriorityQueue(RiskPairComparator())
        val visited = hashSetOf<Pair<Int,Int>>()
        val totalRiskLevel = hashMapOf<Pair<Int, Int>, Int>()

        totalRiskLevel[start] = 0
        priorityQueue.add(RiskPair(start, 0))

        while (priorityQueue.isNotEmpty()){
            val (location, riskLevel) = priorityQueue.remove()
            visited.add(location)

            if (totalRiskLevel.getOrDefault(location, Int.MAX_VALUE) < riskLevel) continue

            for(adj in nextNodes(matrix,Pair(location.first,location.second))){
                if (visited.contains(adj)) continue
                val newRiskLevel = totalRiskLevel.getOrDefault(location, Int.MAX_VALUE) + matrix[adj.first][adj.second]
                if (newRiskLevel < totalRiskLevel.getOrDefault(adj, Int.MAX_VALUE)){
                    totalRiskLevel[adj] = newRiskLevel
                    priorityQueue.add(RiskPair(adj,newRiskLevel))
                }
            }
        }
        return totalRiskLevel[Pair(matrix.size - 1, matrix[0].size - 1)]!!
    }


    fun part1(input: List<String>): Int {
        var matrix = mutableListOf<IntArray>()
        for( i in input) {
            val row = i.map { it.digitToInt() }.toIntArray()
            matrix.add(row)
        }

        var total = dijkstra(Pair(0,0), matrix)
        /*
        for ((x,row) in matrix.withIndex()) {
            for ((y, cell) in row.withIndex()) {
                print(cell)
            }
            println()
        }
        */
        val rowSize = matrix.size
        val colSize = matrix[0].size
        val bigMatrix = MutableList(rowSize * 5) { IntArray(colSize * 5) }

        fun Int.nextLevel(): Int = if (this > 9) abs(this - 9) else this

        for (row in 0 until rowSize){
            for (col in 0 until colSize){
                var counter = 1
                bigMatrix[row][col] = matrix[row][col]

                repeat(4){
                    val newCol = col + (colSize * counter)
                    val newRow = row + (rowSize * counter)
                    bigMatrix[row][newCol] = (bigMatrix[row][col] + it + 1).nextLevel()
                    bigMatrix[newRow][col] = (bigMatrix[row][col] + it + 1).nextLevel()

                    var newCounter = 1
                    repeat(4){ count ->
                        bigMatrix[row + (rowSize * newCounter)][col + (colSize * counter)] = (bigMatrix[row][newCol] + count + 1).nextLevel()
                        ++newCounter
                    }

                    ++counter
                }
            }
        }

        var total2 = dijkstra(Pair(0,0), bigMatrix)

        return total2
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day15_test")
    println(part1(testInput))

    val test = readInput("Day15")
    println(part1(test))
}
