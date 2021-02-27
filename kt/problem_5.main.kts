#!/usr/bin/env kotlin

tailrec fun gcd(x: Int, y: Int): Int = when {
  y == 0 -> x
  else -> gcd(y, x % y)
}

fun lcm(x: Int, y: Int): Int = (x * y) / gcd(x, y)

println((1..19).reduce(::lcm))