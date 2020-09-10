#!/usr/bin/python3
#
# Assignment 3
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu

import random
import math

# help from: MIT Random Walk http://www.mit.edu/~kardar/teaching/projects/chemotaxis(AndreaSchmidt)/random.htm

while True:
    start_location = int(input('What is the start location (1-499)?'))
    if 0 < start_location < 500:
        break

while True:
    minimum_value = int(input('What is the minimum value (0-' + str(start_location - 1) + ')?'))
    if start_location >= minimum_value:
        break

while True:
    max_value = int(input('What is the maximum value (' + str(start_location + 1) + '- 500) ?'))
    if start_location <= max_value:
        break
percentage = float(input('What is the percentage chance of moving right (1-99)?'))

num_of_walk = int(input('How many walks?'))


def one_walk(steps):
    location = start_location
    walked = 0
    while (location < max_value) and (location > minimum_value) and (walked < steps):
        step = random.randint(0, 1)
        if step == 1:
            location += 1
        else:
            location -= 1
        walked += 1
    return [walked, location]


def many_walks(n):
    already_walk = 0
    list1 = []
    while already_walk < n:
        result = one_walk(5000)
        already_walk += 1
        list1.append(result)
    return list1


x_left = []  # empty list to hold values
x_right = []  # empty list to hold values
hit_5000 = []  # empty list to hold values

for x in many_walks(num_of_walk):
    if x[1] == minimum_value:
        x_left.append(x)
    elif x[1] == max_value:
        x_right.append(x)
    elif x[0] == 5000:
        hit_5000.append(x)


def count_mean(listof):
    step = 0
    for c in listof:
        step += int(c[0])
    if len(listof) == 0:
        mean = 0
    else:
        mean = step / len(listof)
    return mean


def online_variance(data):
    # Get help from online sources, 'Online Variance' on Wikipedia
    # url = https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance
    n = 0
    mean = 0.0
    M2 = 0.0
    for x in data:
        n += 1
        delta = x - mean
        mean += delta / n
        M2 += delta * (x - mean)
    if n < 2:
        return float('nan')
    else:
        return M2 / (n - 1)


def count_sedev(listof):
    new_list_for_steps = []
    for c in listof:
        new_list_for_steps.append(c[0])
    result = math.sqrt(online_variance(new_list_for_steps))
    return result


allsteps = []  # put all the steps in different lists together
allsteps.extend(x_left)
allsteps.extend(x_right)
allsteps.extend(hit_5000)

print('Walks that ended on the left:')
print('\tNumber of walks:' + str(len(x_left)))
print('\tMean number of steps per walk:' + str(count_mean(x_left)))
print('\tStandard deviation of number of steps per walk:' + str(count_sedev(x_left)))
print('Walks that ended on the right:')
print('\tNumber of walks:' + str(len(x_right)))
print('\tMean number of steps per walk:' + str(count_mean(x_right)))
print('\tStandard deviation of number of steps per walk:' + str(count_sedev(x_right)))
print('Walks that hit 5000 steps:')
print('\tNumber of walks:' + str(len(hit_5000)))
print('\tMean number of steps per walk:' + str(count_mean(hit_5000)))
print('\tStandard deviation of number of steps per walk:' + str(0.0))
print('All Walks:')
print('\tNumber of walks:' + str(len(x_left) + len(x_right) + len(hit_5000)))
print('\tMean number of steps per walk:' + str(count_mean(allsteps)))
print('\tStandard deviation of number of steps per walk:' + str(count_sedev((x_left) + (x_right) + (hit_5000))))
