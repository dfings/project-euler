from math import prod


def digits(x):
    return map(int, str(x))


def product_of_digits(x) -> int:
    return prod(digits(x))


def sum_of_digits(x) -> int:
    return sum(digits(x))
