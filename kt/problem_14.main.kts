#!/usr/bin/env kotlin

@file:Import("memoize.main.kts")

fun collatzLength(n: Long): Long = memoizedCollatzLength(n)
val memoizedCollatzLength = { n: Long -> 
  when(n) {
    1L -> 1L
    else -> 1L + collatzLength(nextCollatzTerm(n))
  }
}.memoize()

fun nextCollatzTerm(n: Long) = when {
  n % 2L == 0L -> n / 2L
  else -> 3L * n + 1L
}

println((1L..1000000L).asSequence().maxByOrNull(::collatzLength))
