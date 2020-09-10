#!/usr/bin/python3
#
# Assignment 1
# CMPS 5P, Fall 2016
#
# Sinclair(Weiming) Liang
# wliang13@ucsc.edu


import math

length = input("What is the straightaway length (in yards)?\n")
radius = input("What is the turn radius (in yards)?\n")
width = input("What is the track width (in feet)?\n")
depth = input("What is the track depth (in inches)?\n")
pi = math.pi

# unit conversion: http://www.metric-conversions.org/length/yards-to-meters.htm
length_meter = 2 * float(length) * 0.91440
radius_meter = float(radius) * 0.91440
# unit conversion: http://www.metric-conversions.org/length/feet-to-meters.htm
width_meter = float(width) * 0.3048
# unit conversion: http://www.rapidtables.com/convert/length/inch-to-meter.htm
depth_meter = float(depth) * 0.0254

# Calculate the area of two straight ways
s0 = length_meter * width_meter
# Calculate the area of the ring, which is formed by two circles
s1 = (pi * ((radius_meter + width_meter) ** 2) - (pi * ((radius_meter) ** 2)))
# Calculate the Volume
volume = (s0 + s1) * depth_meter

print("Total volume of dirt (in cubic meters) is " + str(volume))
