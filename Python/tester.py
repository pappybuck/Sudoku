import numpy as np
from solver import solve
import time

start = time.time()
quizzes = np.zeros((1000000,81), np.int32)
solutions = np.zeros((1000000,81), np.int32)
for i, line in enumerate(open('sudoku.csv', 'r').read().splitlines()[1:]):
    quiz, solution = line.split(",")
    for j, q_s in enumerate(zip(quiz, solution)):
        q, s = q_s
        quizzes[i, j] = q
        solutions[i, j] = s
print("Import took {:.2f} seconds".format(time.time() - start))

quizzes = quizzes.reshape((-1,9,9))
solutions = solutions.reshape((-1,9,9))
overall = time.time()
start = time.time()
block = 1
for index in range(0,len(quizzes)):
    answer = solve(quizzes[index])
    if answer is None or not np.array_equal(answer, solutions[index]):
        print("Error on quiz {}".format(index+1))
        print("Answer found: {}".format(answer))
        print("Expected answer: {}".format(solutions[index]))
        break
    if index > 100 and (index + 1) % 50000 == 0:
        end = time.time()
        minutes = int((end - overall) // 60)
        seconds = (end - overall) % 60
        if index == len(quizzes) - 1:
            print("Overall time elapsed to solve {} quizzes: {} minutes, {:.2f} seconds".format(len(quizzes), minutes, seconds))
        else:
            print("Solved {} quizzes in {} minutes, {:.4f} seconds".format(index + 1, minutes, seconds))
        minutes = int((end - start) // 60)
        seconds = (end - start) % 60
        print("Block {} took {} minutes, {:.4f} seconds".format(block, minutes, seconds))
        block+=1
        start = time.time()
