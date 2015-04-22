#!/usr/bin/env python 

def primes():
  i = 2
  seen = []

  def divisible_by_seen(x):
    for prime in seen:
      if x % prime == 0:
        return True
      elif prime * prime > x:
        return False
    return False

  while True:
    if not divisible_by_seen(i):
      seen.append(i)
      yield i
    i += 1


def get_index(iterator, i):
  for j, elt in enumerate(iterator):
    if i == j:
      return elt


def main():
  print get_index(primes(), 10000)
  

if __name__ == '__main__':
  main()
