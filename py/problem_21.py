#!/usr/bin/env python

from factor import factor
from functools import cache


@cache
def d(n: int) -> int:
    return 1 + sum(factor(n))


print(sum(x for x in range(10000) if x == d(d(x)) and x != d(x)))
