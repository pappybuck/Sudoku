import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Tester {
  public static void main(String[] args) {
    try {
      Scanner in = new Scanner(new File("../sudoku.csv"));
      in.nextLine();
      Solver solver = new Solver();
      int block = 1;
      int index = 1;
      long start = System.currentTimeMillis();
      long overall = System.currentTimeMillis();
      while (in.hasNextLine()){
        String line = in.nextLine();
        String[] tokens = line.split(",");
        int[][] quiz = new int[9][9];
        int[][] answer = new int[9][9];
        for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
            quiz[i][j] = Character.getNumericValue(tokens[0].charAt(i * 9 + j));
            answer[i][j] = Character.getNumericValue(tokens[1].charAt(i * 9 + j));
          }
        }
        int[][] result = solver.Solve(quiz);
        if (!Arrays.deepEquals(result, answer)) {
          System.out.println("Error on Quiz " + index);
          System.out.println("Solution found: ");
          for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
              System.out.print(result[i][j] + " ");
            }
            System.out.println();
          }
          System.out.println("Solution expected: ");
          for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
              System.out.print(answer[i][j] + " ");
            }
            System.out.println();
          }
          return;
        }
        if (index % 50000 == 0 && index >= 50000){
          long ellapsed = System.currentTimeMillis() - start;
          int seconds = (int) (ellapsed / 1000);
          int millis = (int) (ellapsed % 1000);
          System.out.println("Block " + block + " took " + seconds + " seconds and " + millis + " milliseconds");
          ellapsed = System.currentTimeMillis() - overall;
          seconds = (int) (ellapsed / 1000);
          millis = (int) (ellapsed % 1000);
          System.out.println("Solved " + index + " quizzes in " + seconds + " seconds and " + millis + " milliseconds");
          block++;
          start = System.currentTimeMillis();
        }
        index++;
      }
    } catch (Exception e) {
        System.out.println("File not found");
    }
    System.out.println("All quizzes solved");
  }
}
