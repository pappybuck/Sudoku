package main

func Solve(Board [9][9]int) [9][9]int {
	x, y := 0, 0
	if Board[y][x] != 0 {
		x, y = findNext(Board, x, y)
	}
	if x == -1 || y == -1 {
		return [9][9]int{}
	}
	successorsList := successors(Board, x, y)
	return recSolve(Board, x, y, successorsList)
}

func recSolve(Board [9][9]int, x, y int, successorsList []int) [9][9]int {
	for _, successor := range successorsList {
		Board[y][x] = successor
		newX := x + 1
		newY := y
		if newX == 9 {
			newX = 0
			newY = y + 1
		}
		newX, newY = findNext(Board, x, y)
		if newX == -1 || newY == -1 {
			return Board
		}
		next := successors(Board, newX, newY)
		if len(successorsList) == 0 {
			Board[y][x] = 0
			return [9][9]int{}
		}
		result := recSolve(Board, newX, newY, next)
		if result != [9][9]int{} {
			return result
		}
	}
	Board[y][x] = 0
	return [9][9]int{}
}

func successors(Board [9][9]int, x, y int) []int {
	rowX := Board[y]
	rowY := make([]int, 9)
	for i := 0; i < 9; i++ {
		rowY[i] = Board[i][x]
	}
	block := make([]int, 9)
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			block[i*3+j] = Board[y/3*3+i][x/3*3+j]
		}
	}
	output := make([]int, 0, 9)
	for num := 1; num < 10; num++ {
		for i := 0; i < 9; i++ {
			if rowX[i] == num || rowY[i] == num || block[i] == num {
				goto OUTER
			}
		}
		output = append(output, num)
	OUTER:
	}
	return output
}

func findNext(Board [9][9]int, x, y int) (int, int) {
	for i := y; i < 9; i++ {
		for j := x; j < 9; j++ {
			if Board[i][j] == 0 {
				return j, i
			}
		}
		x = 0
	}
	return -1, -1
}
