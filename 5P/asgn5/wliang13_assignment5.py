#!/usr/bin/python3
#
# Assignment 5
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu

def read_the_file(filename):
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
    newString = ''
    for char in strings:
        if char.isalnum():
            newString += char
    return [newString]

def cleaning_words(listof):
    """
    clean the words, so that evey word can be lowerd cased and ready to be sorted
    :param listof: list of words with mixed cases
    :return: list of words with only lower cases
    """
    clean_words = []
    # new list to store
    for word in listof:
        to_be_added = word.lower()
        clean_words.extend(strping(to_be_added))
    return clean_words

def wordcount(listof):
    """
    Getting the number of the occurrences of each word
    :param listof: list of words, appearing multiple times
    :return: two lists. One contains unique words, the other onw contains the number of occurrences.
    """
    words = []
    count = []
    for word in listof:
        if word in words:
            # if the word is already in the list, just get the index and index this word in the count list, add one into the matching number in count list.
            index = words.index(word)
            count[index] += 1
        else:
            # if the word is not in the list, add it to the words list, and add one to the matching number.
            words.append(word)
            count.append(1)
    return [words, count]

def sorting(to_sort):
    """
    Input a chaotic list, containing only letters and numbers, returns a sorted list, equivalent to the built in sorted() function.
    :param to_sort: a chaotic list
    :return: a sorted list
    """
    A = to_sort[:] # copy the list itself
    A.append(False) # append "False" to the end of the list
    B = []
    B.append(False) # append "False" to the end of the list
    previous = 'z' * 100 #for comparison, assuming that no word would be (z*100)
    last_value = 'A' #indicate the last list we append values to
    while True: #infinite loop until breaks
        if (A[0] == False and B[0] == False):
        # if both lists are semi sorted
            if (len(A) == 1):
                return (B[1:])
                break
            elif (len(B) == 1):
                return (A[1:])
                break
            else:
            # But there are still items in list B and A
                A.append(A.pop(0))
                B.append(B.pop(0))
        if (A[0] == False or B[0] == False):
        #if only one of them is False, choose the other one
            if A[0] == False:
                choose = B[0]
            elif B[0] == False:
                choose = A[0]
        elif(A[0] != False and B[0] != False):
            #if neither of them is False, choose the smaller one.
            if (A[0] >= previous) and (B[0] < previous):
                choose = A[0]
            elif (B[0] >= previous) and (A[0] < previous):
                choose = B[0]
            else:
                if A[0] < B[0]:
                    choose = A[0]
                else:
                    choose = B[0]

        #According last list we appended values to, and the comparison to the last value, we choose which list to add the values to.
        if last_value == 'A':
            if choose >= previous:
                if choose == A[0]:
                    A.append(A.pop(0))
                elif choose == B[0]:
                    A.append(B.pop(0))
            elif choose < previous:
                if choose == A[0]:
                    B.append(A.pop(0))
                    last_value = 'B'
                elif choose == B[0]:
                    B.append(B.pop(0))
                    last_value = 'B'
        elif last_value == 'B':
            if choose >= previous:
                if choose == A[0]:
                    B.append(A.pop(0))
                elif choose == B[0]:
                    B.append(B.pop(0))
            elif choose < previous:
                if choose == A[0]:
                    A.append(A.pop(0))
                    last_value = 'A'
                elif choose == B[0]:
                    A.append(B.pop(0))
                    last_value = 'A'
        previous = choose


def display():
    #user interface, ask for address of the file.
    filename = input("What is the name of your file?\n")
    list_work_on = cleaning_words(read_the_file(filename))
    biglist = wordcount(sorting(cleaning_words((list_work_on))))
    i = 0
    while i < len(biglist[1]):
    # while loop to loop through every item in the list contained in the big list, so that we can display occurrence of the word, and the word itself.
        print(biglist[1][i], biglist[0][i])
        i += 1
    return 0

def test_case():
    # test case, comparing the result from built in sorted() function and sorting() function.
    # test unit
    lis = cleaning_words(read_the_file('./section/resource/test2.txt'))
    result1 = sorted(lis)
    result2 = sorting(lis)
    if result1 == result2:
        print("test passed")
    else:
        print("failed")

    return 0

display()

