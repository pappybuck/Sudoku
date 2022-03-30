import java.io.File

fun main() {
    var reader = File("../sudoku.csv").bufferedReader()
    reader.readLine()
    var index = 0
    while (reader.ready()) {
        print(reader.readLine())
        index++
    }
    print("\n $index")
}