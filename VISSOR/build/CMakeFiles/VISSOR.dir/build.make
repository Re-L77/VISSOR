# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.31

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/re-l/Escritorio/github/VISSOR/VISSOR

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/re-l/Escritorio/github/VISSOR/VISSOR/build

# Include any dependencies generated for this target.
include CMakeFiles/VISSOR.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/VISSOR.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/VISSOR.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/VISSOR.dir/flags.make

CMakeFiles/VISSOR.dir/codegen:
.PHONY : CMakeFiles/VISSOR.dir/codegen

CMakeFiles/VISSOR.dir/src/VISSOR.c.o: CMakeFiles/VISSOR.dir/flags.make
CMakeFiles/VISSOR.dir/src/VISSOR.c.o: /home/re-l/Escritorio/github/VISSOR/VISSOR/src/VISSOR.c
CMakeFiles/VISSOR.dir/src/VISSOR.c.o: CMakeFiles/VISSOR.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/home/re-l/Escritorio/github/VISSOR/VISSOR/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/VISSOR.dir/src/VISSOR.c.o"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -MD -MT CMakeFiles/VISSOR.dir/src/VISSOR.c.o -MF CMakeFiles/VISSOR.dir/src/VISSOR.c.o.d -o CMakeFiles/VISSOR.dir/src/VISSOR.c.o -c /home/re-l/Escritorio/github/VISSOR/VISSOR/src/VISSOR.c

CMakeFiles/VISSOR.dir/src/VISSOR.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing C source to CMakeFiles/VISSOR.dir/src/VISSOR.c.i"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/re-l/Escritorio/github/VISSOR/VISSOR/src/VISSOR.c > CMakeFiles/VISSOR.dir/src/VISSOR.c.i

CMakeFiles/VISSOR.dir/src/VISSOR.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling C source to assembly CMakeFiles/VISSOR.dir/src/VISSOR.c.s"
	/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/re-l/Escritorio/github/VISSOR/VISSOR/src/VISSOR.c -o CMakeFiles/VISSOR.dir/src/VISSOR.c.s

# Object files for target VISSOR
VISSOR_OBJECTS = \
"CMakeFiles/VISSOR.dir/src/VISSOR.c.o"

# External object files for target VISSOR
VISSOR_EXTERNAL_OBJECTS =

VISSOR: CMakeFiles/VISSOR.dir/src/VISSOR.c.o
VISSOR: CMakeFiles/VISSOR.dir/build.make
VISSOR: CMakeFiles/VISSOR.dir/compiler_depend.ts
VISSOR: CMakeFiles/VISSOR.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --bold --progress-dir=/home/re-l/Escritorio/github/VISSOR/VISSOR/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable VISSOR"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/VISSOR.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/VISSOR.dir/build: VISSOR
.PHONY : CMakeFiles/VISSOR.dir/build

CMakeFiles/VISSOR.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/VISSOR.dir/cmake_clean.cmake
.PHONY : CMakeFiles/VISSOR.dir/clean

CMakeFiles/VISSOR.dir/depend:
	cd /home/re-l/Escritorio/github/VISSOR/VISSOR/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/re-l/Escritorio/github/VISSOR/VISSOR /home/re-l/Escritorio/github/VISSOR/VISSOR /home/re-l/Escritorio/github/VISSOR/VISSOR/build /home/re-l/Escritorio/github/VISSOR/VISSOR/build /home/re-l/Escritorio/github/VISSOR/VISSOR/build/CMakeFiles/VISSOR.dir/DependInfo.cmake "--color=$(COLOR)"
.PHONY : CMakeFiles/VISSOR.dir/depend

