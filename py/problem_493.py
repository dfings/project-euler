#!/usr/bin/env python 

NUM_COLORS = 7
NUM_PER_COLOR = 10
NUM_TO_PICK = 20
SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK

cache = {}


def all_balls_picked(urn):
  return sum(urn) == SUM_ALL_PICKED
    

def count_colors(urn):
  return len([val for val in urn if val < NUM_PER_COLOR])


def compute_counts(urn):
  key = tuple(sorted(urn))
  counts = cache.get(key)
  if not counts:  
    if all_balls_picked(urn):
      counts = [count_colors(urn), 1]
    else:
      counts = [0, 0]
      for i in range(len(urn)):
        for _ in range(urn[i]):
          urn[i] -= 1
          child_counts = compute_counts(urn)
          counts[0] += child_counts[0]
          counts[1] += child_counts[1]
          urn[i] += 1
    cache[key] = counts
  return counts
    
  
if __name__ == '__main__':
  starting_urn = [NUM_PER_COLOR] * NUM_COLORS
  counts = compute_counts(starting_urn)
  print("Total colors = {}".format(counts[0]))
  print("Total picks = {}".format(counts[1]))
  print("Average = {0:0.9f}".format(float(counts[0]) / counts[1]))
