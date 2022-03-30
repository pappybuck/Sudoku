using System;
using System.Collections.Generic;
class Solver
{
    public static int[][] Solve(int[][] board)
    {
        (int y, int x) = find_next(board, 0, 0);
        if (x == -1 || y == -1)
        {
            return new int[][] {};
        }
        return rec_solve(board, x, y, successors(board, x, y));
    }

    static private int[][] rec_solve(int[][] board, int x, int y, List<int> successors_list){
        foreach (int successor in successors_list){
            board[y][x] = successor;
            int new_x = x+1;
            int new_y = y;
            if (new_x == 9){
                new_x = 0;
                new_y++;
            }
            (new_y, new_x) = find_next(board, new_x, new_y);
            if (new_x == -1 || new_y == -1){
                return board;
            }
            List<int> next = successors(board, new_x, new_y);
            if (next.Count == 0){
                board[y][x] = 0;
                continue;
            }
            int [][] result = rec_solve(board, new_x, new_y, next);
            if (result.Length != 0){
                return result;
            }
            board[y][x] = 0;
        }
        return new int[][] {};
    }
    static private List<int> successors(int[][] board, int x, int y){
        if (board[y][x] != 0){
            (y,x) = find_next(board, x, y);
        }
        if (x == -1 || y == -1){
            return new List<int>();
        }
        List<int> x_row = new List<int>(board[y]);
        List<int> y_row = new List<int>();
        for (int i = 0; i < board.Length; i++){
            y_row.Add(board[i][x]);
        }
        List<int> block = new List<int>();
        for (int i = 0; i < 3; i++){
            int block_y = (y / 3) * 3 + i;
            for (int j = 0; j < 3; j++){
                int block_x = (x / 3) * 3 + j;
                block.Add(board[block_y][block_x]);
            }
        }
        List<int> output = new List<int>();
        for (int num = 1; num < 10; num++)
        {
            if (!x_row.Contains(num) && !y_row.Contains(num) && !block.Contains(num))
            {
                output.Add(num);
            }
        }
        return output;
    }

    static private (int y, int x) find_next(int[][] board, int x, int y){
        for (int i = y; i <board.Length; i++)
        {
            for (int j = x; j < board[i].Length; j++)
            {
                if (board[i][j] == 0)
                {
                    return (i, j);
                }
            }
            x = 0;
        }
        return (-1, -1);
    }
}