#!/usr/bin/env python

from math import prod


def find_right_triangle():
    for a in range(1, 333):
        for b in range(a, 500):
            c = 1000 - a - b
            if a * a + b * b == c * c:
                return (a, b, c)


triangle = find_right_triangle()
print(triangle)
print(prod(triangle))
