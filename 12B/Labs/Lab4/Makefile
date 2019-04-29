#------------------------------------------------------------------------------
# Name: Sinclair Liang
# E-Mail: wliang13@ucsc.edu
# lab4
# Makefile for FileReverse.c with macros
#------------------------------------------------------------------------------

FLAGS = -std=c99 -Wall
SOURCESFILE = charType.c
SOURCES = charType.c README Makefile
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.u17 lab4

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(SOURCESFILE)

$(OJECTS): $(SOURCESFILE)
	gcc -c $(FLAGS) $(SOURCESFILE)

clean:
	rm -f $(EXEBIN) $(OBJECTS)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
