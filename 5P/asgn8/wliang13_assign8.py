#!/usr/bin/python3
#
# Assignment 8
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu

import random

def creat_grid():
    board = []
    for row in range(20):
        board.append([])
        for column in range(20):
            board[row].append('.')
    for row in board:
        print(" ".join(row))
    return 0

#creat_grid()

def creat_grid_1(n):
    dictx = {}
    for i in range(n):
        for j in range(n):
            dictx.update({(i,j):'$'})
    return dictx

# print(creat_grid_1(20))

class zombie:
    # density
    # disease_chance
    # spread
    # mortality
    def __init__(self,n,density,disease_chance):
        self.dictx = {}
        self.density = density
        self.disease_chance = disease_chance
        for i in range(n):
            for j in range(n):
                cell = random.random()
                if cell < density:
                    r = random.random
                    if r < disease_chance:
                        self.dictx.update({(i,j): 1})
                    else:
                        self.dictx.update({(i,j): 0})
                else:
                    self.dictx.update({(i,j): -1})
        return 0

    def printif(self):
        for j in range(self.n):
            listx = []
            for k in range (self.n):
                if self.dictx[(j,k)] == -1:
                    listx.append('.')
                else:
                    listx.append(str(self.dictx[(j,k)]))
            print("".join())



    def getneighbor(self,x,y):
        listx = []
        for i in range(x-1, x+2):
            for j in range(y-1, y-2):
                try:
                    listx.append(self.dictx[(i,j)])
                except:
                    continue
        return listx

    def updatecell(self,x,y):
        dictnew = {}


    if test_dict(x,y) == -1:
        neighbor = getneighbor(point)
        //count the number of healthy neighbors
        //for each healthy neighbor
            generate a random and compare it with 'birth'
            if it is less than birth:
             cell becomes healthy
             break
            else:
                -1




def updatecell (point, birth_chance, spread_chance, mortality_rate, disease_duration):
    x = point[0]
    y = point[1]
    temp_grid = test_dict.copy()
    healthy_neighbor = 0
    sick_neighbor = 0
    neighbor = getneighbor(point)
    for member in neighbor:
        if member == 0:
            healthy_neighbor += 1
    for member in neighbor:
        if member > 0:
            sick_neighbor += 1
    if test_dict[point] == -1:
        for times in range(healthy_neighbor):
            probs = random.random()
            if probs <= birth_chance:
                temp_grid[point] = 0
                break
            else:
                temp_grid[point] = -1
    elif test_dict[point] == 0:
        for times in range(sick_neighbor):
            probs = random.random()
            if probs <= spread_chance:
                temp_grid[point] = 1
                break
            else:
                temp_grid[point] = 0
    elif test_dict[point] == 1:
        for times in range(disease_duration):
            probs = random.random()
            if probs <= mortality_rate:
                temp_grid[point] += 1
                break
            else:
                temp_grid[point] = 0

