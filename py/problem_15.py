#!/usr/bin/env python

from functools import cache
from math import factorial


@cache
def count_paths(x, y):
    if x < 0 or y < 0:
        return 0
    elif x == 0 and y == 0:
        return 1
    else:
        return count_paths(x - 1, y) + count_paths(x, y - 1)


print(count_paths(20, 20))  # Direct approach
print(factorial(40) // (factorial(20) * factorial(20)))  # C(40, 20)
