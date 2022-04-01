use std::fs::File;
use std::io::{BufRead, BufReader};

fn main() {
    let f = File::open("../sudoku.csv").expect("Unable to open file");
    let f = BufReader::new(f);
    let mut index = 1;
    for line in f.lines() {
        let line = line.expect("Unable to read line");
        let split: Vec<&str> = line.split(",").collect();
        println!("Quiz: {}", split[0]);
        println!("Solution: {}", split[1]);
        println!("Index: {}", index);
        index += 1;
    }
}