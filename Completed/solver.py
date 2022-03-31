import numpy as np

def succ(board, x, y):
    if board[y][x] != 0:
        return {}
    y_row = [row[x] for row in board]
    x_row = board[y]
    start_x = (x // 3) * 3
    start_y = (y // 3) * 3
    if start_x == 9 or start_y == 9:
        return {}
    slice = board[start_y:start_y + 3, start_x:start_x + 3]
    output = {}
    for num in range(1,len(board)+1):
        if num not in y_row and num not in x_row and num not in slice:
            output[num] = num
    return output

def rec_solve(board, x, y, choices={}):
    if board[y][x] != 0:
        for i in range(y, len(board)):
            for j in range(x, len(board[i])):
                if board[i][j] == 0:
                    x = j
                    y = i
                    break
            if board[y][x] == 0:
                choices = succ(board, x, y)
                break
            elif i == len(board)-1 and j == len(board[i])-1:
                if validate_board(board):
                    return board
                return None
            x = 0

    for successor in choices:
        board[y][x] = successor
        new_x = x + 1
        new_y = y
        if new_x == len(board):
            new_x = 0
            new_y += 1
        if new_y == len(board):
            return board
        answer = rec_solve(board.copy(), new_x, new_y, succ(board, new_x, new_y))
        if answer is not None:
            return answer
    return None

def validate_board(board):
    for num in range(1, len(board)+1):
        for x in range(len(board)):
            y_row = [row[x] for row in board]
            if np.count_nonzero(board[x] == num) != 1 and np.count_nonzero(y_row == num) != 1:
                return False
    return True

def solve(board):
    x = 0
    y = 0
    for row in range(len(board)):
        for col in range(len(board[row])):
            if board[row][col] == 0:
                x = col
                y = row
                break
        if board[y][x] == 0:
            break
    answer = rec_solve(board.copy(), x,y, succ(board, x, y))
    if answer is not None:
        return np.array(answer)
    else:
         return None