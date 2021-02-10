#!/usr/bin/env python 

from primes import primes2

def get_index(iterator, i):
  for j, elt in enumerate(iterator):
    if i == j:
      return elt


def main():
  print(get_index(primes2(), 10000))
  

if __name__ == '__main__':
  main()
