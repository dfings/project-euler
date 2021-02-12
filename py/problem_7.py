#!/usr/bin/env python 

from primes import primes

def get_index(iterator, i):
  for j, elt in enumerate(iterator):
    if i == j:
      return elt


def main():
  print(get_index(primes(), 10000))
  

if __name__ == '__main__':
  main()
