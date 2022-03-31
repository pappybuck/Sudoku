package main

import (
	"encoding/csv"
	"fmt"
	"os"
	"reflect"
	"time"
)

func main() {
	f, err := os.Open("../sudoku.csv")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer f.Close()

	csvReader := csv.NewReader(f)
	line, err := csvReader.Read()
	line, err = csvReader.Read()
	index:= 1
	block := 1
	overall := time.Now()
	start := time.Now()
	for err == nil {
		quizUnformatted:= line[0]
		var quiz [9][9]int
		var solution [9][9]int
		solutionUnformatted:= line[1]
		for i:= 0; i < 9; i++ {
			for j:= 0; j < 9; j++ {
				quiz[i][j]= int(quizUnformatted[i*9+j]) - '0'
				solution[i][j]= int(solutionUnformatted[i*9+j]) - '0'
			}
		}
		result := Solve(quiz)
		if !reflect.DeepEqual(result, solution) {
			fmt.Printf("Error on quiz %d\n", index)
			fmt.Printf("Answer found: %v\n", result)
			fmt.Printf("Expected answer: %v\n", solution)
			break
		}
		if index % 50000 == 0 && index >= 50000{
			duration := time.Now().Sub(start)
			overallDuration := time.Now().Sub(overall)
			minutes := int(duration.Minutes())
			seconds := int(duration.Seconds()) % 60
			milliseconds := int(duration.Milliseconds()) % 1000
			overallMinutes := int(overallDuration.Minutes())
			overallSeconds := int(overallDuration.Seconds()) % 60
			overallMilliseconds := int(overallDuration.Milliseconds()) % 1000
			fmt.Printf("Block %d took %d minutes, %d seconds, and %d milliseconds.\n", 
			block, minutes, seconds, milliseconds)
			start = time.Now()
			block++
			if index != 1000000 {
				fmt.Printf("Solved %d quizzes in %d minutes, %d seconds, and %d milliseconds.\n", 
				index, overallMinutes, overallSeconds, overallMilliseconds)
			} else {
				fmt.Printf("Overall time: %d minutes, %d seconds, and %d milliseconds.\n",
				overallMinutes, overallSeconds, overallMilliseconds)
			}
		}
		line, err = csvReader.Read()
		index++
	}
}