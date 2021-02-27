#!/usr/bin/env python

def square(x: int) -> int:
    return x * x


def square_of_sum(x: int) -> int:
    return square(sum(range(1, x + 1)))


def sum_of_squares(x: int) -> int:
    return sum(map(square, range(1, x + 1)))


print(square_of_sum(100) - sum_of_squares(100))
