#!/usr/bin/env python 

from __future__ import print_function

def primes():
  i = 2
  sieve = {}

  while True:
    res = sieve.get(i)
    if not res:
      yield i
      sieve[i * i] = [i]
    else:
      del sieve[i]
      for j in res:
        sieve.setdefault(i + j, []).append(j)      
    i += 1


def get_index(iterator, i):
  for j, elt in enumerate(iterator):
    if i == j:
      return elt


def main():
  print(get_index(primes(), 10000))
  

if __name__ == '__main__':
  main()
