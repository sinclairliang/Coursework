#------------------------------------------------------------------------------
#   Makefile for Dictionary ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = DictionaryClient
MAINCLASS  = DictionaryClient
SOURCES    = Dictionary.java DictionaryInterface.java DictionaryClient.java DuplicateKeyException.java KeyNotFoundException.java
CLASSES    = Dictionary.class DictionaryInterface.class DictionaryClient.class Dictionary\$$Node.class DuplicateKeyException.class KeyNotFoundException.class
SUBMITFILES = Dictionary.java Makefile KeyNotFoundException.java DictionaryInterface.java DuplicateKeyException.java DictionaryClient.java README

SUBMIT    = submit cmps012b-pt.u17 lab7

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE)

submit: $(SUBMITFILES)
	$(SUBMIT) $(SUBMITFILES)
