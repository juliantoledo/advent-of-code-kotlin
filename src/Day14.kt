fun main() {

    fun insertRules(template:String, rules: HashMap<String, String>): String {
        val sb = StringBuilder()
        for (i in 0 .. template.length-2) {
            val pair = template.subSequence(i, i+2)
            val rule = rules[pair]
            if (!rule.isNullOrBlank()) {
                sb.append(pair[0]).append(rule)
            } else {
                sb.append(pair[0])
            }
        }
        sb.append(template.last())
        return sb.toString()
    }

    fun part1(input: List<String>): Int {
        var rules = hashMapOf<String, String>()
        var template = ""
        input.forEach { line ->
            if (line.contains("->")) {
                line.splitTo(" -> ") { a, b ->
                    rules[a] = b
                }
            } else {
                if (line.isNotEmpty()) template = line
            }
        }

        for (i in 1..10) {
            template = insertRules(template, rules)
            //println(template)
            println("step $i ${template.length}")
        }

        var group = template.groupBy { it }
        var values = group.values.sortedBy { it.size }
        return values.last().size - values.first().size
    }

    fun insertRules2(template:HashMap<String, Long>, rules: HashMap<String, String>): HashMap<String, Long> {
        var temp = linkedMapOf<String, Long>()//template.clone() as HashMap<String, Int>
        template.keys.forEach{ pair ->
            val rule = rules[pair]
            if (!rule.isNullOrBlank()) {
                val newPair1 = "${pair[0]}$rule"
                val newPair2 = "$rule${pair[1]}"

                //println("$pair(${(template[pair] ?: 0)}) + $rule -> $newPair1 $newPair2")

                temp[newPair1] = (template[pair] ?: 0) + (temp[newPair1] ?: 0)
                temp[newPair2] = (template[pair] ?: 0) + (temp[newPair2] ?: 0)
            }
        }
        return temp
    }

    fun part2(input: List<String>): Int {
        var rules = hashMapOf<String, String>()
        var template = hashMapOf<String, Long>()
        input.forEach { line ->
            if (line.contains("->")) {
                line.splitTo(" -> ") { a, b ->
                    rules[a] = b
                }
            } else {
                if (line.isNotEmpty()) {
                    val temp = line.asSequence().windowed(size = 2, step = 1).toList().forEach { pair ->
                        template["${pair[0]}${pair[1]}"] = 1
                    }
                }
            }
        }

        println(template)
        for (i in 1..40) {
            template = insertRules2(template, rules)
            println(template)
            println("step $i ${template.size}")

            var letters = hashMapOf<Char, Long>()
            template.keys.forEach{ pair ->
                letters[pair[0]] = (letters[pair[0]] ?: 0) + (template[pair] ?: 0)
                letters[pair[1]] = (letters[pair[1]] ?: 0) + (template[pair] ?: 0)
            }
            var values = letters.values.sorted()
            println((values.last() - values.first())/2)
        }

        //var group = template.groupBy { it }
        //var values = group.values.sortedBy { it.size }
        //return values.last().size - values.first().size

        return 0
    }

    val testInput = readInput("Day14_test")
    println(part1(testInput))

    println(part2(testInput))

    val test = readInput("Day14")
    println(part2(test))
    // 2959788056211
    // tried 2959788056212
    // tried 2959788056210
    //
}
