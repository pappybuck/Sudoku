#include <stdio.h>
#include "string.h"
#include "solver.h"
#include "time.h"
#include "math.h"
#define MAX_LINE_LENGTH 170
FILE *stream;
int main() {
    errno_t err = fopen_s(&stream,"../sudoku.csv","r");
    if (err != 0){
        printf("Could not read file\n");
        return 1;
    }
    int index = 0;
    int block = 1;
    char line[MAX_LINE_LENGTH] = {0};
    clock_t overall = clock();
    clock_t start = clock();
    while(fgets(line, MAX_LINE_LENGTH, stream)){
        if (index == 0){
            index++;
            continue;
        }
        char *split = strtok(line,",");
        char *quiz = split;
        split = strtok(NULL,",");
        char *solution = split;
        solution[strcspn(solution, "\n")] = 0;
        solve(quiz);
        if (strcmp(quiz,solution)){
            printf("Error on Quiz %d \n", index);
            printf("Solution found: %s \n", quiz);
            printf("Solution expected: %s \n", solution);
            fclose(stream);
            return 1;
        }
        if (index % 50000 == 0 && index>=50000){
            start = clock() - start;
            time_t current = clock() - overall;
            printf("Block %d took %.3f seconds.\n", block, ((double) start / CLOCKS_PER_SEC));
            printf("Solved %d quizzes in %.3f seconds.\n",index,((double ) current) / CLOCKS_PER_SEC);
            block++;
            start = clock();
        }
        index++;
    }
    printf("All quizzes passed \n");
    fclose(stream);
}
