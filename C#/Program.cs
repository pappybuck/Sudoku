using System;

class Solver
{
    static int[][] Solve(int[][] board)
    {
        (int y, int x) = find_next(board, 0, 0);
        if (x == -1 || y == -1)
        {
            return new int[][] {};
        }
        int[][] result = rec_solve(board, y, x, successors(board, y, x));
        
    }

    static private int[][] rec_solve(int[][] board, int x, int y, List<int> successors){
        return board;
    }
    static private List<int> successors(int[][] board, int x, int y){
        if (board[x][y] != 0){
            (y,x) = find_next(board, x, y);
        }
        if (x == -1 || y == -1){
            return new List<int>();
        }
        int[] x_row = board[x];
        int[] y_row = new int[board.Length];
        for (int i = 0; i < board.Length; i++){
            y_row[i] = board[i][x];
        }
        int[,] block = new int [3,3];
        for (int i = 0; i < 3; i++){
            int block_y = y / 3 + i;
            for (int j = 0; j < 3; j++){
                int block_x = x / 3 + j;
                block[i,j] = board[block_y][block_x];
            }
        }
        List<int> output = new List<int>();
        for (int num = 1; num < 10; num++)
        {
            if (x_row.Contains(num) || y_row.Contains(num))
            {
                output.Add(num);
            }
            else {
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        if (block[i,j] == num){
                            output.Add(num);
                            goto end;
                        }
                    }
                }
            }
            end:
                continue;
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