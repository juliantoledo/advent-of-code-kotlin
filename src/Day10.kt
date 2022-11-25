import java.util.Stack

class Day10 {

    class Route(private val line: String) {

        val completion = Stack<Char>()
        var score = run {
            line.forEach { c ->
                when (c) {
                    '(', '[', '{', '<' -> completion.push(c.closure())
                    else -> if (completion.pop() != c) return@run c.scoreSyntax()
                }
            }
            0
        }.also {
            completion.reverse()
        }

    }

    fun part1(input: List<String>): Int {
        return input.map { Route(it) }.sumOf { it.score }
    }

    fun part2(input: List<String>): Long {
        return input.map { Route(it) }
            .filter { it.score == 0 }.map { it.completion.score() }
            .sorted().let { it[it.size / 2] }
    }

}

private fun Char.closure() = when (this) {
    '(' -> ')'
    '[' -> ']'
    '{' -> '}'
    '<' -> '>'
    else -> '?'
}

private fun Char.scoreSyntax() =
    when (this) {
        '(', ')' -> 3
        '[', ']' -> 57
        '{', '}' -> 1197
        '<', '>' -> 25137
        else -> 0
    }

private fun Char.scoreCompletion() =
    when (this) {
        '(', ')' -> 1
        '[', ']' -> 2
        '{', '}' -> 3
        '<', '>' -> 4
        else -> 0
    }

private fun Stack<Char>.score(): Long {
    var score = 0L
    forEach { c ->
        score = score * 5 + c.scoreCompletion()
    }
    return score
}

fun main() {

    readInput("day10_test").let { testInput ->
        Day10().apply {
            println(part1(testInput))
        }
    }

    readInput("day10").let { input ->

        println("\nPART 1")
        Day10().apply {
            println(part1(input))
        }
        println("\nPART 2")
        Day10().apply {
            println(part2(input))
        }
    }

}