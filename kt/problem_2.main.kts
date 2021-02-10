#!/usr/bin/env kotlin

var (total, a, b) = listOf(0, 1, 1)
while (b < 4000000) {
  if (b % 2 == 0) total += b
  b = a + b
  a = b - a
}
println(total)
