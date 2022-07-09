//
// Created by Patrick on 4/1/2022.
//
#include "solver.h"
#include <malloc.h>

char * rec_solve(char *board, int index, char *successors);
char * succ(char *board, int index);
int find_next(char *board);

char * solve(char *board){

    int index = find_next(board);
    if (index == -1){
        return board;
    }
    char * successors = succ(board, index);
    return rec_solve(board, index, successors);
}

char * rec_solve(char *board, int index, char *successors){

    for (char *successor = successors; *successor; successor ++){
        board[index] = *successor;
        int new_index = find_next(board);
        if (new_index == -1){
            free(successors);
            return board;
        }
        char * next = succ(board, new_index);
        char * result = rec_solve(board, new_index, next);
        if (result[new_index] != 48){
            free(successors);
            return board;
        }
    }
    free(successors);
    board[index] = 48;
    return board;
}

char * succ(char *board, int index){
    char row_x[9];
    char col_y[9];
    char block[9];
    int x = index % 9;
    int y = index / 9;
    for (int i = 0; i < 9; i++){
        row_x[i] = board[i+y*9];
        col_y[i] = board[x+i*9];
    }
    for (int i = 0; i < 3; i++){
        for (int j =0; j < 3; j++){
            block[i*3+j] = board[(y/3*3+i)*9+x/3*3+j];
        }
    }
    char *output = calloc(9,sizeof(char));
    index = 0;
    for (int num = 49; num < 58; num++){
        for (int i = 0; i < 9; i++){
            if (row_x[i] == num || col_y[i] == num || block[i] == num){
                goto OUTER;
            }
        }
        output[index] = num;
        index ++;
        OUTER:
            continue;
    }
    return output;
}

int find_next(char *board){
    int index = 0;
    for (int i = 0;i < 81; i++){
        if (board[i] == '0'){
            return i;
        }
    }
    return -1;
}



