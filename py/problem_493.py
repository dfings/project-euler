#!/usr/bin/env python 

NUM_COLORS = 7
NUM_PER_COLOR = 10
NUM_TO_PICK = 20

class Solver(object):
  
  def __init__(self):
    self.balls_picked = 0
    self.urn = [NUM_PER_COLOR] * NUM_COLORS
    self.memoized = {}
    
  def CountColors(self):
    count = 0
    for val in self.urn:
      if val != NUM_PER_COLOR:
        count += 1
    return count

  def PickNextBall(self):
    if self.balls_picked == NUM_TO_PICK:
      return [1, self.CountColors()]
    
    encoded = tuple(sorted(self.urn))
    counts = self.memoized.get(encoded, None)
    if counts:
      return counts
    
    counts = [0, 0]
    self.balls_picked += 1
    for i in xrange(NUM_COLORS):
      for j in xrange(NUM_PER_COLOR):
        if self.urn[i] > j:
          self.urn[i] -= 1
          returned = self.PickNextBall()
          counts[0] += returned[0]
          counts[1] += returned[1]
          self.urn[i] += 1
    self.balls_picked -= 1
    self.memoized[encoded] = counts
    return counts
    
  
def main():
  solver = Solver()
  counts = solver.PickNextBall()
  print "Total colors = %d" % counts[1]
  print "Total picks = %d" % counts[0]
  print "Average = %0.9f" % (float(counts[1]) / counts[0])
  

if __name__ == '__main__':
  main()