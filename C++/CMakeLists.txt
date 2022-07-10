cmake_minimum_required(VERSION 3.22)
project(C__)

set(CMAKE_CXX_STANDARD 14)
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_CURRENT_SOURCE_DIR})
add_executable(Cpp
        Tester.cpp Solver.cpp Solver.h)
