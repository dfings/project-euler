#!/usr/bin/env python

total, a, b = 0, 1, 1
while b < 4000000:
  if not b % 2:
    total += b
  a, b = b, a + b
print(total)
