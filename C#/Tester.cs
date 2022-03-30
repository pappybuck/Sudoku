using System;
using System.IO;
using System.Diagnostics;
class Tester 
{
    public static void Main(string[] args)
    {
        using (var reader = new StreamReader("../sudoku.csv"))
        {
            reader.ReadLine();
            int index = 1;
            int block_index = 1;
            Stopwatch overall = new Stopwatch();
            Stopwatch block = new Stopwatch();
            overall.Start();
            block.Start();
            while(!reader.EndOfStream)
            {
                int[][] quiz = new int[9][];
                int[][] solution = new int[9][];
                var line = reader.ReadLine();
                var both = line.Split(',');
                for (int i = 0; i < 9; i++)
                {
                    quiz[i] = new int[9];
                    solution[i] = new int[9];
                    for (int j = 0; j < 9; j++)
                    {
                        quiz[i][j] = int.Parse(both[0][i * 9 + j].ToString());
                        solution[i][j] = int.Parse(both[1][i * 9 + j].ToString());
                    }

                }
                int[][] answer = Solver.Solve(quiz);
                for (int i = 0; i < 9; i++)
                {
                    for (int j = 0; j < 9; j++)
                    {
                        if (answer[i][j] != solution[i][j])
                        {
                            Console.WriteLine("Error on quiz " + index);
                            Console.WriteLine("Answer found: " + answer);
                            Console.WriteLine("Expected answer: " + solution);
                            return;
                        }
                    }
                }
                if (index % 50000 ==0 && index >= 50000){
                    block.Stop();
                    Console.WriteLine("Block {0} took {1} minutes, {2} seconds, and {3} milliseconds", block_index, block.Elapsed.Minutes , block.Elapsed.Seconds, block.Elapsed.Milliseconds);
                    block.Reset();
                    block.Start();
                    block_index++;
                    if (index != 1000000){
                        overall.Stop();
                        Console.WriteLine("Solved {0} quizzes in {1} minutes, {2} seconds, and {3} milliseconds", index, overall.Elapsed.Minutes, overall.Elapsed.Seconds, overall.Elapsed.Milliseconds);
                        overall.Start(); 
                    }else {
                        Console.WriteLine("All tests passed");
                        overall.Stop();
                        Console.WriteLine("Overall time: {0} minutes, {1} seconds, and {2} milliseconds", overall.Elapsed.Minutes, overall.Elapsed.Seconds, overall.Elapsed.Milliseconds);
                    }
                }
                index++;
            }
           }
    }
}