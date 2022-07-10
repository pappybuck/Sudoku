//
// Created by Patrick on 7/9/2022.
//
#include "Solver.h"
#include <fstream>
#include <iostream>
#include <sstream>
#include <time.h>

using namespace std;


int main(){
    ifstream infile("../sudoku.csv");
    string line;
    getline(infile, line);
    int index = 1;
    int block = 1;
    auto start = clock();
    auto overall_start = clock();
    while (getline(infile, line)) {
        int quiz[81];
        int answer[81];
        auto pos = line.find(",");
        string quizLine = line.substr(0, pos);
        string answerLine = line.substr(pos + 1);
        for (int i = 0; i < 81; i++) {
            quiz[i] = quizLine[i] - '0';
            answer[i] = answerLine[i] - '0';
        }
        solve(quiz);
        for (int i = 0; i < 81; i++) {
            if (quiz[i] != answer[i]) {
                cout << "Error on Quiz " << index << endl;
                ostringstream os;
                for (int c: quiz) {
                    os << c;
                }
                cout << "Solution found: " << os.str() << endl;
                ostringstream os2;
                for (int c: answer) {
                    os2 << c;
                }
                cout << "Solution expected: " << os2.str() << endl;
                return 1;
            }
        }
        if (index % 50000 == 0 && index>=50000) {
            auto end = clock();
            auto milli = (end - start) % 1000;
            auto seconds = (end - start) / 1000;
            cout << "Block " << block << " took " << seconds << " seconds and " << milli << " milliseconds" << endl;
            milli = (clock() - overall_start) % 1000;
            seconds = (clock() - overall_start) / 1000;
            cout << "Solved " << index << " quizzes in " << seconds << " seconds and " << milli << " milliseconds" << endl;
            block++;
            start = clock();
        }
        index++;
    }
    cout << "All quizzes passed" << endl;
    infile.close();
    return 0;
}