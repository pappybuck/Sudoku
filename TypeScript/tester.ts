import * as fs from 'node:fs';
import {solve} from './solver';

fs.readFile('../sudoku.csv', function(err, data) {
    if (err) {
        throw err;
    }
    let lines = data.toString().split('\n');
    let block = 1;
    let start =  Date.now();
    const overall = Date.now();
    for (let i = 0; i < lines.length; i++) {
        let quiz: Number[] = [];
        let answer: Number[] = [];
        if (i == 0) {
            continue;
        }
        let line = lines[i].split(',');
        for (let j = 0; j < line[0].length; j++) {
            quiz.push(Number(line[0][j]));
            answer.push(Number(line[1][j]));
        }
        quiz = solve(quiz);
        if (quiz.toString() !== answer.toString()) {
            console.log("Error on Quiz " + i);
            console.log("Solution found: " + quiz.toString());
            console.log("Solution expected: " + answer.toString());
            return;
        }
        if (i % 50000 == 0 && i >= 50000){
            let ellapsed = Date.now() - start;
            let millis = ellapsed % 1000;
            let seconds = Math.floor(ellapsed / 1000);
            let minutes = Math.floor(seconds / 60);
            console.log("Block " + block + " took " + minutes + " minutes, " + seconds + " seconds, and " + millis + " milliseconds");
            ellapsed = Date.now() - overall;
            millis = ellapsed % 1000;
            seconds = Math.floor(ellapsed / 1000);
            minutes = Math.floor(seconds / 60);
            console.log("Solved " + i + " quizzes in " + minutes + " minutes, " + seconds + " seconds, and " + millis + " milliseconds");
            block++;
            start = Date.now();
        }
    }
});
