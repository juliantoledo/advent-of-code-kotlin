data class Node(val name: String, val nodes: MutableList<Node> = mutableListOf()) {
    override fun toString() = name
}

fun main() {

    fun printNodes(nodes: MutableList<Node>) {
        nodes.forEach{ node ->
            print(" ${node.name}:${node.nodes}")
        }
        println()
    }

    fun load(input: List<String>): MutableList<Node> {
        var nodes = mutableListOf<Node>()
        input.forEach { line -> line.splitTo("-") { a, b ->
                val nodeA = nodes.find { it.name == a } ?: Node(a).also { nodes.add(it) }
                val nodeB = nodes.find { it.name == b } ?: Node(b).also { nodes.add(it) }
                nodeA.nodes.add(nodeB)
                nodeB.nodes.add(nodeA)
            }
        }
        //printNodes(nodes)
        return nodes
    }

    fun addNext(nodeA: Node, nodeB: Node, path: ArrayList<Node>, paths: MutableList<ArrayList<Node>>): ArrayList<Node> {
        path.add(nodeA)
        var newPath = path
        if (nodeA.name != nodeB.name) {
            nodeA.nodes.forEach{ child ->
                newPath = path.clone() as ArrayList<Node>
                if (child.name[0].isUpperCase() || !path.contains(child)) {
                    addNext(child, nodeB, newPath, paths)
                }
            }
        } else {
            paths.add(newPath)
        }
        return path
    }

    fun addNext2(nodeA: Node, nodeB: Node, path: ArrayList<Node>, paths: MutableList<ArrayList<Node>>, visitedCount: HashMap<String, Int>): ArrayList<Node> {
        path.add(nodeA)
        var newPath = path
        if (nodeA.name != nodeB.name) {
            nodeA.nodes.forEach{ child ->
                newPath = path.clone() as ArrayList<Node>
                if (child.name[0].isUpperCase()) {
                    addNext2(child, nodeB, newPath, paths, visitedCount)
                } else {
                    var subpath = path.filter { it.name[0].isLowerCase()}.groupBy { it.name }
                    // && subpath.get(child.name)!!.size < 2
                    var temp = subpath.count{ it.value.size == 2 }
                    if ( child.name != "start" && (subpath[child.name] == null
                                || subpath[child.name]!!.size < 2) && temp < 2) {
                        addNext2(child, nodeB, newPath, paths, visitedCount)
                    }
                }
            }
        } else {
            paths.add(newPath)
        }
        return path
    }

    fun paths(nodes: MutableList<Node>): Int {
        var paths = mutableListOf<ArrayList<Node>>()
        var start = nodes.find { it.name == "start" }
        var end = nodes.find { it.name == "end" }

        var visitedCount = hashMapOf<String, Int>()

        if (start != null && end != null) {
            addNext2(start, end, arrayListOf<Node>(), paths, visitedCount)
        }

        // print
        paths.forEach{ nodes ->
            nodes.forEach{ node ->
                print("${node.name},")
            }
            println("")
        }
        return paths.size
    }


    fun part1(input: List<String>): Int {
        var nodes = load(input)
        return paths(nodes)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day12_test")
    println(part1(testInput))

    val testInput2 = readInput("Day12_test2")
    println(part1(testInput2))

    val testInput3 = readInput("Day12_test3")
    println(part1(testInput3))

    val test = readInput("Day12")
    println(part1(test))

}
