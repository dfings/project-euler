#!/usr/bin/env python 

NUM_COLORS = 7
NUM_PER_COLOR = 10
NUM_TO_PICK = 20
SUM_ALL_PICKED = NUM_COLORS * NUM_PER_COLOR - NUM_TO_PICK

class Solver(object):
  
  def __init__(self):
    self.urn = [NUM_PER_COLOR] * NUM_COLORS
    self.cache = {}

  def AllBallsPicked(self):
    return sum(self.urn) == SUM_ALL_PICKED
    
  def CountColors(self):
    return len([val for val in self.urn if val < NUM_PER_COLOR])

  def ComputeCounts(self):
    key = tuple(sorted(self.urn))
    counts = self.cache.get(key)
    if not counts:  
      if self.AllBallsPicked():
        counts = [self.CountColors(), 1]
      else:
        counts = [0, 0]
        for i in xrange(NUM_COLORS):
          for j in xrange(self.urn[i]):
            self.urn[i] -= 1
            child_counts = self.ComputeCounts()
            counts[0] += child_counts[0]
            counts[1] += child_counts[1]
            self.urn[i] += 1
      self.cache[key] = counts
    return counts
    
  
if __name__ == '__main__':
  solver = Solver()
  counts = solver.ComputeCounts()
  print "Total colors = %d" % counts[0]
  print "Total picks = %d" % counts[1]
  print "Average = %0.9f" % (float(counts[0]) / counts[1])
