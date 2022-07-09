mod solver;

use std::fs::File;
use std::io::{BufRead, BufReader};
use std::time::{Instant};
fn main() {
    let f = File::open("../sudoku.csv").expect("Unable to open file");
    let f = BufReader::new(f);
    let mut index = 1;
    let mut block = 1;
    let overall = Instant::now();
    let mut start = Instant::now();
    for line in f.lines().skip(1) {
        let line = line.expect("Unable to read line");
        let split: Vec<&str> = line.split(",").collect();
        let mut quiz = Vec::new();
        let mut answer = Vec::new();
        for i in 0..81 {
            quiz.push(char::to_digit(split[0].chars().nth(i).unwrap(), 10).unwrap());
            answer.push(char::to_digit(split[1].chars().nth(i).unwrap(), 10).unwrap());
        }
        solver::solve(& mut quiz);
        if quiz != answer {
            println!("Error on Quiz {}", index);
            println!("Solution found: {:?}", quiz);
            println!("Solution expected: {:?}", answer);
            return;
        }
        if index % 50000 == 0 && index >= 50000 {
            println!("Block {} took {} seconds.", block, start.elapsed().as_secs());
            println!("Solved {} quizzes in {} seconds.", index, overall.elapsed().as_secs());
            block += 1;
            start = Instant::now();
        }
        index += 1;
    }
    println!("All quizzes solved in {} seconds.", overall.elapsed().as_secs());
}