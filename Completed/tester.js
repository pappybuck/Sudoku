"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const fs = __importStar(require("node:fs"));
const solver_1 = require("./solver");
fs.readFile('../sudoku.csv', function (err, data) {
    if (err) {
        throw err;
    }
    let lines = data.toString().split('\n');
    let block = 1;
    let start = Date.now();
    const overall = Date.now();
    for (let i = 0; i < lines.length; i++) {
        let quiz = [];
        let answer = [];
        if (i == 0) {
            continue;
        }
        let line = lines[i].split(',');
        for (let j = 0; j < line[0].length; j++) {
            quiz.push(Number(line[0][j]));
            answer.push(Number(line[1][j]));
        }
        quiz = (0, solver_1.solve)(quiz);
        if (quiz.toString() !== answer.toString()) {
            console.log("Error on Quiz " + i);
            console.log("Solution found: " + quiz.toString());
            console.log("Solution expected: " + answer.toString());
            return;
        }
        if (i % 50000 == 0 && i >= 50000) {
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
