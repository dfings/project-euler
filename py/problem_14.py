#!/usr/bin/env python 

from functools import cache

@cache
def collatz_length(n):
  return 1 if n == 1 else 1 + collatz_length(next_term(n))


def next_term(n):
  return n / 2 if n % 2 == 0 else 3 * n + 1
  

max_value = max(range(1, 1000000), key=collatz_length)
print(max_value, collatz_length(max_value))
