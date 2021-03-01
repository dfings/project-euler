#!/usr/bin/env python

from fibonacci import fibonacci


def find_index() -> int:
    for i, x in enumerate(fibonacci()):
        if len(str(x)) >= 1000:
            return i + 1


print(find_index())
