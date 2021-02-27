#!/usr/bin/env python 

import os

def load_numbers():
  with open(os.path.join(os.path.dirname(__file__), '../data/problem_13_data')) as f:
      return map(int, f.read().splitlines())


print(str(sum(load_numbers()))[:10])
