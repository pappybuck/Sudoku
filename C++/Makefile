CC=g++
CFLAGS= -Wall -g -O3 -std=c++11

%.o: %.cpp 
	$(CC) -c -o $@ $< $(CFLAGS)

Cpp: Tester.o Solver.o 
	$(CC) -o $@ $^ $(CFLAGS) 

clean:
	rm -f *.o Cpp.exe Cpp