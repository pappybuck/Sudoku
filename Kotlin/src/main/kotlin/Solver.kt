public fun Solve(Board: Array<Array<Int>>): Array<Array<Int>> {
    var x = 0
    var y = 0
    if (Board[y][x] != 0) {
        val pair = findNext(Board, x, y)
        x = pair.first
        y = pair.second
        if (x == -1 || y == -1){
            return emptyArray()
        }
    }
    val successorsList = successors(Board, x, y)
    return recSolve(Board, x, y, successorsList)
}

private fun recSolve(Board: Array<Array<Int>>, x : Int, y : Int, successorsList: List<Int>): Array<Array<Int>>{

    for (successor in successorsList){
        Board[y][x] = successor
        var newX = x + 1
        var newY = y
        if (newX == 9){
            newY++
            newX = 0
        }
        val new = findNext(Board, newX, newY)
        newX = new.first
        newY = new.second
        if (newX == -1 || newY == -1){
            return Board
        }
        val next = successors(Board, newX, newY)
        if (next.isEmpty()){
            Board[y][x] = 0
            continue
        }
        val result = recSolve(Board, newX, newY, next)
        if (result.isNotEmpty()){
            return result
        }
        Board[y][x] = 0
    }
    return emptyArray()
}

private fun successors(Board: Array<Array<Int>>, x: Int, y : Int): List<Int> {

    val xRow = Board[y]
    val yRow = arrayOfNulls<Int>(9)
    for (i in Board.indices){
        yRow[i] = Board[i][x]
    }
    var block = ArrayList<Int>()
    for (i in 0..2){
        val blockY = (y / 3) * 3 + i
        for (j in 0..2){
            val blockX = (x / 3) * 3 + j
            block.add(Board[blockY][blockX])
        }
    }
    var output = ArrayList<Int>()
    for (num in 1..9){
        if (!xRow.contains(num) && !yRow.contains(num) && !block.contains(num)){
            output.add(num)
        }
    }
    return output
}

private fun findNext(Board: Array<Array<Int>>, x: Int, y: Int): Pair<Int, Int>{
    var tempX = x
    for (i in y until Board.size){
        for (j in tempX until Board[i].size){
            if (Board[i][j] == 0){
                return Pair(j, i)
            }
        }
        tempX = 0
    }
    return Pair (-1, -1)
}