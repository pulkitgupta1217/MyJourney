cmake_minimum_required(VERSION 3.8)
project(first_c_project)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        03-02_switch2.c
        )
add_executable(first_c_project ${SOURCE_FILES})