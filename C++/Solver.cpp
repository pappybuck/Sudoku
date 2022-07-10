//
// Created by Patrick on 7/9/2022.
//

#include <algorithm>
#include "Solver.h"

int *getSuccessors(int *board, int index);

void rec_solve(int *board, int *successors, int index);

using namespace std;

void solve(int *board){
    auto index_ptr = find(board, board + 81, 0);
    if (index_ptr == board + 81) {
        return;
    }
    auto index = index_ptr - board;
    auto successors = getSuccessors(board, index);
    rec_solve(board, successors, index);
}

void rec_solve(int *board, int *successors, int index) {
    for (auto successor = successors; *successor != 0; successor++) {
        board[index] = *successor;
        auto next_ptr = find(board, board + 81, 0);
        if (next_ptr == board + 81) {
            delete[] successors;
            return;
        }
        auto next = next_ptr - board;
        auto next_successors = getSuccessors(board, next);
        rec_solve(board, next_successors, next);
        if (board[next] != 0) {
            delete[] successors;
            return;
        }
    }
    delete[] successors;
    board[index] = 0;
    return;
}


int *getSuccessors(int *board, int index) {
    int row_x[9];
    int col_x[9];
    int box_x[9];
    int x = index % 9;
    int y = index / 9;
    for (int i = 0; i < 9; i++){
        row_x[i] = board[i * 9 + x];
        col_x[i] = board[y * 9 + i];
    }
    for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; j++){
            box_x[i * 3 + j] = board[(y / 3 * 3 + i) * 9 + (x / 3 * 3 + j)];
        }
    }
    int *successors = new int[9];
    int count = 0;
    for (int i = 1; i <= 9; i++){
        if (find(row_x, row_x + 9, i) == row_x + 9 &&
            find(col_x, col_x + 9, i) == col_x + 9 &&
            find(box_x, box_x + 9, i) == box_x + 9) {
            successors[count] = i;
            count++;
        }
    }
    for (int i = count; i < 9; i++){
        successors[i] = 0;
    }
    return successors;
}
