#!/usr/bin/python3
#
# Assignment 2
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu


#with the help from Harvard CS50 recursion: 'https://www.youtube.com/watch?v=t4MSwiqfLaY'

max_number = int(input("What is the maximum number?"))
def search(mins, maxs):

    '''
    :param mins: intially 0, becomes medium number when answer == G
    :param maxs: max number, becomes medium number when answer == L
    :return: search the medium number for this.
    '''

    guess = (mins + maxs)//2
    print("Is your number (G)reater, (L)ess, or (E)qual to", str(guess), "?")
    answer = input()
    #If the number gets guessed correctly
    if answer == 'E':
        print("I guessed your number! It was", str(guess), "!")
    #If the number is greater than the program guessed, we passed the guess number to min;
    elif answer == 'G':
        search(guess+1, maxs)
    #If the number is greater than the program guessed, we passed the guess number to max;
    elif answer == 'L':
        search(mins, guess-1)

search(0, max_number)
