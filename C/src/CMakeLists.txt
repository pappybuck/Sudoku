cmake_minimum_required(VERSION 3.21)
project(C C)

set(CMAKE_C_STANDARD 99)

add_executable(C tester.c solver.h solver.c)
