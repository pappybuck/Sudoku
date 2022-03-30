import java.io.File
import java.time.Duration
import java.time.Instant
import kotlin.math.abs

fun main(args: Array<String>) {
    val reader = File("../sudoku.csv").bufferedReader()
    reader.readLine()
    var index = 1
    val overall = Instant.now()
    var start = Instant.now()
    var block = 1
    while (reader.ready()) {
        var split = reader.readLine().split(",")
        val quiz = Array(9){Array(9){0} }
        val solution = Array(9){Array(9){0} }
        for (i in quiz.indices) {
            for (j in quiz.indices){
                quiz[i][j] = split[0][i*9+j].digitToInt()
                solution[i][j] = split[1][i*9+j].digitToInt()
            }
        }
        val result = Solve(quiz)
        if (!result.contentDeepEquals(solution)){
            println("Error on quiz $index")
            println("Answer found: ${result.toString()}")
            println("Expected answer: ${solution.toString()}")
            break
        }
        if (index % 50000 == 0 && index >= 50000) {
            val end = Instant.now()
            val overallEnd = Instant.now()
            val duration = Duration.between(start, end)
            val overallDuration = Duration.between(overall, overallEnd)
            println(
                "Block $block took ${abs(duration.toMinutesPart())} minutes, " +
                        "${abs(duration.toSecondsPart())} seconds and " +
                        "${abs(duration.toMillisPart())} milliseconds."
            )
            block++
            start = Instant.now()
            if (index != 1000000) {
                println(
                    "Solved $index quizzes in ${abs(overallDuration.toMinutesPart())} minutes, " +
                            "${abs(overallDuration.toSecondsPart())} seconds and " +
                            "${overallDuration.toMillisPart()} milliseconds."
                )
            } else {
                println("All tests passed")
                println(
                    "Overall time: ${abs(overallDuration.toMinutesPart())} minutes, " +
                            "${abs(overallDuration.toSecondsPart())} seconds and " +
                            "${overallDuration.toMillisPart()} milliseconds."
                )
            }
        }
        index++
    }
}