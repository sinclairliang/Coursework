#!/usr/bin/python3
#
# Assignment 6
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu

import os.path

def ask_for_file():
    """
    Ask user to input names of books until the user hits 'Enter' without inputting anything.
    :return: a list of file names
    """
    names = []
    condition = True
    while condition == True:
        name = input("type in the address of books. hit 'Enter' when finished\n")
        if len(name) != 0 and os.path.isfile(name) == True:
        #check if the file exists
            names.extend(name.split(','))
        elif len(name) != 0 and os.path.isfile(name) == False:
            print("File not found")
        elif len(name) == 0:
            return names
            condition = False
    return names

def read_the_file(filename):
    # Copied directly from Assignment 5
    file = open(filename,"r")
    #expect user to enter the full adress of the file
    read_file = file.read().split()
    #make every word into items in a list
    return read_file

def strping(strings):
    """
    :param strings: put in a string
    :return: a string only contains letters and numbers
    """
    # Copied directly from Assignment 5
    newString = ''
    for char in strings:
        if char.isalnum():
            newString += char
    return [newString]

def cleaning_words(listof):
    """
    clean the words, so that evey word can be lowered cased and ready to be sorted
    :param listof: list of words with mixed cases
    :return: list of words with only lower cases
    """
    # Copied directly from Assignment 5
    clean_words = []
    # new list to store
    for word in listof:
        to_be_added = word.lower()
        clean_words.extend(strping(to_be_added))
    return clean_words

def count_word(listof):
    # Source code from class
    d = dict()
    for word in listof:
        d[word] = d.get(word, 0) + 1
    return d

def ngram(word, n):
    """
    It can only slice a word
    :param word: a word to be sliced
    :param n: how long the slice would be
    :return: a list of sliced pieces of that word.
    """
    listx = []
    length = len(word) - n + 1
    for k in range(length):
        listx.append(word[k:k+n])
    return listx

def gram(listof, n):
    """
    By calling ngram(), this function returns a whole list of words
    :param listof: A list of words to be sliced
    :param n: how long the slice would be
    :return: A list of sliced words.
    """
    listx = []
    for word in listof:
        listx.extend(ngram(word,n))
    return listx

def get_all_dicts(listof):
    """
    An operation to put a list of words from file to different dictionaries.
    :param listof: A list of words to be analyzed.
    :return: modify the global dictionaries.
    """
    biglist = []
    # biglist is to store result of every "slicing", it should be deleted each time.
    for k in range(3):
        biglist.extend(gram(listof, k+1))
        if k==0:
            dictx['dict2'].update(count_word(biglist))
        elif k==1:
            dictx['dict3'].update(count_word(biglist))
        elif k==2:
            dictx['dict4'].update(count_word(biglist))
        del biglist[:]
    dictx['dict1'].update(count_word(listof))

def get_total_dict(listof):
    """
    An operation to put a list of words in all the books to different dictionaries.
    :param listof: A list of words to be analyzed.
    :return: modify the global dictionaries.
    """
    biglist = []
    # biglist is to store result of every "slicing", it should be deleted each time.
    for k in range(3):
        biglist.extend(gram(listof, k+1))
        if k==0:
            dictx2['dict2'].update(count_word(biglist))
        elif k==1:
            dictx2['dict3'].update(count_word(biglist))
        elif k==2:
            dictx2['dict4'].update(count_word(biglist))
        del biglist[:]
    dictx2['dict1'].update(count_word(listof))

def dict2tuple(dictx):
    """
    Converts a dictionary to tuples.
    :param dictx: A dictionary
    :return: a list of tuples
    """
    list1 = []
    for key, value in dictx.items():
        tup = (value, key)
        list1.append(tup)
    return list1

#Two global dictionaries.
dictx = {'dict1':{}, 'dict2':{}, 'dict3':{}, 'dict4':{}}
dictx2 = {'dict1':{}, 'dict2':{}, 'dict3':{}, 'dict4':{}}

def display():
    """
    main function, user interface
    :return: printing the results.
    """
    names = ask_for_file()
    megalist = []
    for name in names:
        get_all_dicts(cleaning_words(read_the_file(name)))
        tuple1 = sorted(dict2tuple(dictx['dict1']), reverse=True)
        tuple2 = sorted(dict2tuple(dictx['dict2']), reverse=True)
        tuple3 = sorted(dict2tuple(dictx['dict3']), reverse=True)
        tuple4 = sorted(dict2tuple(dictx['dict4']), reverse=True)
        #print result for one book
        print('\n\n---- Statistics for file'+str(name)+':'+'\n')
        print("\n------Letter frequencies------")
        for e,f in tuple2[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx['dict2'].values()))))
        print('\n--------Words--------')
        for e,f in tuple1[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx['dict1'].values()))))
        print('\n--------Bigrams--------')
        for e,f in tuple3[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx['dict3'].values()))))
        print('\n--------Trigrams--------')
        for e,f in tuple4[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx['dict4'].values()))))
        dictx['dict1'].clear()
        dictx['dict2'].clear()
        dictx['dict3'].clear()
        dictx['dict4'].clear()
        megalist.extend(cleaning_words(read_the_file(name)))
    if len(names) > 0:
        get_total_dict(megalist)
        tuple5 = sorted(dict2tuple(dictx2['dict1']), reverse=True)
        tuple6 = sorted(dict2tuple(dictx2['dict2']), reverse=True)
        tuple7 = sorted(dict2tuple(dictx2['dict3']), reverse=True)
        tuple8 = sorted(dict2tuple(dictx2['dict4']), reverse=True)
        #print overall result
        print('\n\n---- Overall statistics:')
        print("--------Letter frequencies--------")
        for e,f in tuple6[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx2['dict2'].values()))))
        print('\n')
        print('--------Words--------')
        for e,f in tuple5[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx2['dict1'].values()))))
        print('\n')
        print('--------Bigrams--------')
        for e,f in tuple7[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx2['dict3'].values()))))
        print('\n')
        print('--------Trigrams--------')
        for e,f in tuple8[:25]:
            print('{0:8} : {1:4} ({2:05.3f}%)'.format(f,e, (100*e/sum(dictx2['dict4'].values()))))

    return 0

display()

