#!/usr/bin/env python

from fibonacci import fibonacci
from itertools import takewhile

print(sum(filter(lambda x: x % 2 == 0, takewhile(lambda x: x < 4000000, fibonacci()))))
