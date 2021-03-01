#!/usr/bin/env python

from fibonacci import fibonacci
from itertools import takewhile

print(1 + sum(1 for _ in takewhile(lambda x: len(str(x)) < 1000, fibonacci())))
