#!/usr/bin/env python

from primes import primes


def get_index(iterator, i):
    for j, elt in enumerate(iterator):
        if i == j:
            return elt
    return None


print(get_index(primes(), 10000))
