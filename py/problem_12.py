#!/usr/bin/env python 

from itertools import accumulate, count, dropwhile
from factor import factor

print(next(dropwhile(lambda x: len(factor(x)) <= 500, accumulate(count()))))
