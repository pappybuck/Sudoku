CC=gcc
CFLAGS=-std=c99 -g -O2

ODIR=.
OBJ = solver.o tester.o

$(ODIR)/%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

C: $(OBJ)
	$(CC) $(OBJ) -o $@

clean:
	rm -f $(OBJ) $(C)