import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
  public int[][] Solve(int[][] board) {
    int index = findNext(board, -1, -1);
    if (index == -1) {
      return board;
    }
    int row = index / 9;
    int col = index % 9;
    ArrayList<Integer> successors = getSuccessors(board, row, col);

    return recSolve(board, successors, row, col);
  }

  public int[][] recSolve(int[][] board, ArrayList<Integer> successors, int row, int col){
    for (int successor : successors){
      board[row][col] = successor;
      int index = findNext(board, row, col);
      if (index == -1) {
        return board;
      }
      int newRow = index / 9;
      int newCol = index % 9;
      ArrayList<Integer> newSuccessors = getSuccessors(board, newRow, newCol);
      int[][] result = recSolve(board, newSuccessors, newRow, newCol);
      if (result[newRow][newCol] != 0){
        return result;
      }
    }
    board[row][col] = 0;
    return board;
  }

  private ArrayList<Integer> getSuccessors(int[][] board, int row, int col){
    int[] row_x = board[row];
    int[] col_x = new int[9];
    int[] block = new int[9];
    for (int i = 0; i < 9; i++) {
      col_x[i] = board[i][col];
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        block[i * 3 + j] = board[i + row - row % 3][j + col - col % 3];
      }
    }
    ArrayList<Integer> successors = new ArrayList<Integer>(9);
    for (int i = 1; i <= 9; i++) {
      int finalI = i;
      if (Arrays.stream(row_x).anyMatch(x -> x == finalI) || Arrays.stream(col_x).anyMatch(x -> x == finalI) || Arrays.stream(block).anyMatch(x -> x == finalI)) {
        continue;
      }
      successors.add(i);
    }
    return successors;
  }

  private int findNext(int[][] board, int row, int col) {
    for (int i = 0; i < 9; i++) {
      if (i < row ){
        continue;
      }
      for (int j = 0; j < 9; j++) {
        if (j <= col && i == row) {
          continue;
        }
        if (board[i][j] == 0) {
          return i * 9 + j;
        }
      }
    }
    return -1;
  }

}
