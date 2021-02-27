#!/usr/bin/env python 

from functools import cache

NUM_COLORS = 7
NUM_PER_COLOR = 10
NUM_TO_PICK = 20
SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK


def all_balls_picked(urn):
  return sum(urn) == SUM_ALL_PICKED
    

def count_colors(urn):
  return sum(val < NUM_PER_COLOR for val in urn)


def pick(urn, color):
  return [count - 1 if index == color else count 
          for index, count in enumerate(urn) ]


def sum_tuples(iters):
  return tuple(map(sum, zip(*iters)))
  

def compute_counts(urn):
  return compute_counts_cached(tuple(sorted(urn)))


@cache
def compute_counts_cached(urn):
  if all_balls_picked(urn):
    return (count_colors(urn), 1)
  else:
    return sum_tuples(compute_counts(pick(urn, i))
                      for i in range(len(urn))
                      for _ in range(urn[i]))

  
if __name__ == '__main__':
  starting_urn = [NUM_PER_COLOR] * NUM_COLORS
  counts = compute_counts(starting_urn)
  print("Total colors = {}".format(counts[0]))
  print("Total picks = {}".format(counts[1]))
  print("Average = {0:0.9f}".format(float(counts[0]) / counts[1]))
