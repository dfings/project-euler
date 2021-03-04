#!/usr/bin/env python

from itertools import product
from math import prod


def products_of_three_digit_numbers():
    return map(prod, product(range(100, 1000), range(100, 1000)))


def is_palindrome(value):
    value_str = str(value)
    return value_str == value_str[::-1]


print(max(filter(is_palindrome, products_of_three_digit_numbers())))
