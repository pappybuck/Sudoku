import csv
from solver import solve
import time

with open('../sudoku.csv', 'r') as f:
    reader = csv.reader(f, delimiter=',')
    reader.__next__()
    overall = time.time()
    start = time.time()
    index = 1
    block = 1
    for line in reader:
        quiz = []
        solution = []
        for row in range(0, 9):
            quiz.append([])
            solution.append([])
            for col in range(0, 9):
                quiz[row].append(int(line[0][row * 9 + col]))
                solution[row].append(int(line[1][row * 9 + col]))
        quiz = solve(quiz)
        if quiz != solution:
            print("Error on quiz {}".format(index+1))
            print("Answer found: {}".format(quiz))
            print("Expected answer: {}".format(solution))
            break
        if index > 100 and index % 50000 == 0:
            end = time.time()
            minutes = int((end - overall) // 60)
            seconds = (end - overall) % 60
            if index == 1000000:
                print("Overall time elapsed to solve {} quizzes: {} minutes, {:.2f} seconds".format(index, minutes, seconds))
            else:
                print("Solved {} quizzes in {} minutes, {:.4f} seconds".format(index, minutes, seconds))
                minutes = int((end - start) // 60)
                seconds = (end - start) % 60
                print("Block {} took {} minutes, {:.4f} seconds".format(block, minutes, seconds))
            block+=1
            start = time.time()
        index+=1 