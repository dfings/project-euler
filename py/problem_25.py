#!/usr/bin/env python

from fibonacci import fibonacci
from itertools import takewhile

LIMIT = 10 ** 999
print(1 + sum(1 for _ in takewhile(lambda x: x < LIMIT, fibonacci())))
