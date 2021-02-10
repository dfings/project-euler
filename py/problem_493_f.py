#!/usr/bin/env python 

NUM_COLORS = 7
NUM_PER_COLOR = 10
NUM_TO_PICK = 20
SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK

def all_balls_picked(urn):
  """ Determines if we've picked all the balls we need to from an urn. """
  return sum(urn) == SUM_ALL_PICKED


def count_colors_picked(urn):
  """ Counts the number of colors that have been picked from the urn. """
  return len([val for val in urn if val < NUM_PER_COLOR])


def get_next_states(head, ball_count, tail):
  """Gets the next urn states giving that we're considering picking a ball at 
     the given position that has the given number currentlyl left in the urn."""
  if not ball_count:
    return []
  elif not tail:
    return [head + [ball_count - 1]] * ball_count
  else:
    return [head + [ball_count - 1] + tail]* ball_count


def child_gen(head, ball_count, tail):
  """Generates one new urn state for each possible ball that could be picked."""
  next_states = get_next_states(head, ball_count, tail)
  if not tail:
    return next_states
  else:
    return next_states + child_gen(head + [ball_count], tail[0], tail[1:])


def generate_children(urn):
  """Helper for using childGenerator on an urn."""
  return child_gen([], urn[0], urn[1:])


cache = {}
def compute_counts(urn):
  """ Computes (total colors, totalleaves) for this subtree. """
  key = tuple(sorted(urn))
  res = cache.get(key)
  if not res:
    if all_balls_picked(urn):
      res = [count_colors_picked(urn), 1]
    else:
      child_counts = map(compute_counts, generate_children(urn))
      res = list(map(sum, zip(*child_counts)))
    cache[key] = res
  return res

  
def main():
  starting_urn = [NUM_PER_COLOR] * NUM_COLORS
  counts = compute_counts(starting_urn)
  print("Total colors = {}".format(counts[0]))
  print("Total picks = {}".format(counts[1]))
  print("Average = {0:0.9f}".format(float(counts[0]) / counts[1]))
  

if __name__ == '__main__':
  main()