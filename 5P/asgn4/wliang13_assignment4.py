#!/usr/bin/python3
#
# Assignment 4
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu



def letter_to_number(letter):
    """
    convert ascill numbers to positions in alphabet.
    :param letter:
    :return: the position in alphabet
    """
    for c in letter:
        number = int(ord(c) - ord('A'))
        return number


def encrypt(text, keyword):
    """
    Encrypte text using the keyword
    :param text:
    :param keyword:
    :return: encrypted message
    """
    new_message = ""  # a new string to store only letters
    for ch in text:
        if ch.isalpha():
            new_message += ch
    text = new_message.upper()  # converts each letter in text to uppercase letters
    keyword = keyword.upper()  # converts each letter in key to upper case letters
    k = 0
    result = ""  # a new string to store encrypted messages
    while k < len(text):
        final_number = (letter_to_number(text[k]) + letter_to_number(keyword[k % len(keyword)]))
        k += 1
        result += str((chr((final_number % 26) + ord('A'))))
        if k % 8 == 0:
            result += " "
    return result


def decrypt(text, keyword):
    """
    decrypte text using the keyword
    :param text:
    :param keyword:
    :return: decrypted message
    """
    text = text.upper()  # converts each letter in text to uppercase letters
    keyword = keyword.upper()  # converts each letter in key to uppercase letters
    k = 0
    i = 0
    result = ""  # a new string to store decrypted messages
    while i < len(text):
        if text[i].isalpha():
            final_number = (letter_to_number(text[i]) - letter_to_number(keyword[k % len(keyword)]))
            result += str((chr((final_number % 26) + ord('A'))))
            k += 1
        else:
            result += str(text[i])
        i += 1
    return result


def main():
    message = input('What is your message?\n')
    key = input('What is your key?\n')
    while True:
        EorD = input('(E)ncrypt or (D)ecrypt?\n').upper()
        if EorD == "E" or "D":
            break
    if EorD == 'E':
        print('Encrypted message:', encrypt(message, key))
    elif EorD == 'D':
        print('Decrypted message:', decrypt(message, key))
    return 0


main()
