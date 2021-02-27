#!/usr/bin/env python

from itertools import takewhile
from primes import primes

print(sum(takewhile(lambda n: n < 2000000, primes())))
