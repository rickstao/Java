#------------------------------------------------------------------------------
#  Makefile for CMPS 101 Programming Assignment 4
#  #
#  make                     makes GraphTest
#  #  make GraphClient         makes GraphClient
#  make clean               removes binaries
#  #  make checkFind           tests GraphTest for memory leaks on in3
#  make checkClient         tests GraphClient for memory leaks
#  #------------------------------------------------------------------------------

BASE_SOURCES   = Graph.c List.c
BASE_OBJECTS   = Graph.o List.o
HEADERS        = Graph.h List.h
COMPILE        = gcc -c -std=c99 -Wall
LINK           = gcc -o
REMOVE         = rm -f
MEMCHECK       = valgrind --leak-check=full

FindPath : FindPath.o $(BASE_OBJECTS)
	$(LINK) FindPath FindPath.o $(BASE_OBJECTS)

GraphTest : GraphTest.o $(BASE_OBJECTS)
	$(LINK) GraphTest GraphTest.o $(BASE_OBJECTS)

FindPath.o : FindPath.c $(HEADERS)
	$(COMPILE) FindPath.c

GraphTest.o : GraphTest.c $(HEADERS)
	$(COMPILE) GraphTest.c

$(BASE_OBJECTS) : $(BASE_SOURCES) $(HEADERS)
	$(COMPILE) $(BASE_SOURCES)

clean :
	$(REMOVE) FindPath GraphTest FindPath.o GraphTest.o $(BASE_OBJECTS)

checkFind : FindPath
	$(MEMCHECK) FindPath in3 junk3

checkClient : GraphTest
	$(MEMCHECK) GraphTest

submit :
	submit cmps101-pt.u17 pa4 README List.c List.h Graph.c Graph.h GraphTest.c FindPath.c Makefile
