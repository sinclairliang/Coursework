#!/usr/bin/python3
#
# Assignment 7
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu

import VideoPoker
import random



def get_all_cards():
    """
    Go through all the combinations of suits and ranks.
    :return: A whole list of cards, in strings.
    """
    suits = ['C','D','H','S']
    ranks = ['2','3','4','5','6','7','8','9','T','J','Q','K', 'A']
    list_of_cards = []
    for suit in suits:
        for rank in ranks:
            list_of_cards.append(rank+suit)
    random.shuffle(list_of_cards)
    return list_of_cards

def cards_to_rankstring(cardslist):
    """
    Convert
    :param cardslist:
    :return: a string to represent ranks of cards.
    """
    rank_list = [0,0,0,0,0,0,0,0,0,0,0,0,0]
    rank_string = []
    for card in cardslist:
        if card[0].isdigit():
            rank_list[int(card[0]) - 2] += 1
        else:
            # for 10 to King
            if card[0] == 'T':
                rank_list[8] += 1
            if card[0] == 'J':
                rank_list[9] += 1
            if card[0] == 'Q':
                rank_list[10] += 1
            if card[0] == 'K':
                rank_list[11] += 1
            if card[0] == 'A':
                rank_list[12] += 1
    for num in rank_list:
        rank_string.append(str(num))
    return (''.join(rank_string))

def cards_to_suitstring(cardslist):
    """
    Convert
    :param cardslist:
    :return: a string to represent ranks of cards.
    """
    suit_list = [0,0,0,0]
    suit_string = []
    for card in cardslist:
        if card[1] == 'C':
            #Club
            suit_list[0] += 1
        if card[1] == 'D':
            #Diamond
            suit_list[1] += 1
        if card[1] == 'H':
            #Heart
            suit_list[2] += 1
        if card[1] == 'S':
            #Spade
            suit_list[3] += 1
    for num in suit_list:
        suit_string.append(str(num))
    return (''.join(suit_string))

def count2(ranklist):
    """
    To count how many pairs are there in a hand of cards
    :param ranklist: a string of ranks
    :return: the number of pairs
    """
    occur2 = 0
    for num in ranklist:
        if num == '2':
            occur2 += 1
    return occur2

def tell_if_pair(ranklist):
    """
    to judge if the pair is above 10.
    :param ranklist: a string of ranks
    :return: Boolean Value to indicate whether it is a valid pair or not
    """
    for position in range(9,13):
        for card in ranklist[position]:
            if card == '2':
                return True

def rank_cards(rank_list, suit_list):
    """
    Deliver different messages according to defferent combination of cards
    :param rank_list: a list of ranks
    :param suit_list: a list of suits
    :return: messages regarding to conditions
    """
    if ('11111' in rank_list) and ('5' in suit_list):
        if rank_list == '0000000011111':
            return 'Royal Flush'
        else:
            return 'Straight flush'
    elif '4' in rank_list:
        return'Four of a kind'
    elif ('3' in rank_list):
        if '2' in rank_list:
            return 'Full house'
        else:
            return 'Three of a kind'
    elif '5' in suit_list:
        return 'Flush'
    elif '11111' in rank_list:
        return'Straight'
    elif '2' in rank_list:
        if count2(rank_list) == 2:
            return 'Two pair'
        elif count2(rank_list) == 1 and tell_if_pair(rank_list) == True:
            return('pair')
        else:
            return 'Sorry, you did not win.'
    else:
        return 'Sorry, you did not win.'


def display():
    """
    main function, coordinates various functions to perform games
    :return: 0
    """
    condition = True
    while condition is True:
        # keep asking until it is whitn [10, 1000]
        credits = int(input("How many credits do you want to bet?"))
        if credits > 9 and credits < 1001:
            condition = False

    vp = VideoPoker.VideoPoker()
    vp.display_credits(credits)
    while credits > 0:
        # keep playing until user runs out of credits

        deck = get_all_cards()
        hands = deck[:5]
        del deck[:5]
        # Get a hand of 5 cards
        credit_to_bet = vp.get_credits_bet()
        # ask user to bet
        credits -= credit_to_bet
        # subtract the bet from thr available credits
        vp.set_cards(hands)
        # display cards
        held_card_indices = vp.get_held_cards()
        # indicate if user keeps cards.
        #my_deck = hand_of_cards(get_all_cards(),10)
        # get a new deck
        #new_hand = my_deck[:5]
        # get a new hand for next round
        if len(held_card_indices) > 0:
            # if user holds cards, keep them in the same positions as in the old one
            for j in range(5):
                if j not in held_card_indices:
                    hands[j] = deck.pop(0)
                #new_hand = hands
        vp.set_cards(hands)
        message = rank_cards(cards_to_rankstring(hands), cards_to_suitstring(hands))
        vp.set_status(message)
        # display again
        vp.await_continue_button()
        # keep the window open

        # evaluate what the user have.
        if message == 'Royal Flush':
            credits += credit_to_bet*250
        elif message == 'Straight flush':
            credits += credit_to_bet*50
        elif message == 'Four of a kind':
            credits += credit_to_bet*25
        elif message == 'Full house':
            credits += credit_to_bet*9
        elif message == 'Flush':
            credits += credit_to_bet*6
        elif message == 'Straight':
            credits += credit_to_bet*4
        elif message == 'Three of a kind':
            credits += credit_to_bet*3
        elif message == 'Two pair':
            credits += credit_to_bet*2
        elif message == 'pair':
            credits += credit_to_bet*1
        else:
            credits += 0
        vp.display_credits(credits)
        #vp.set_status(message)
    vp.set_status('Game Over')
    return 0


display()


