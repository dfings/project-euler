#!/usr/bin/env kotlin

import java.util.TreeMap

object CollatzLength {
  val cache = TreeMap<Long, Long>()

  fun compute(n: Long): Long = when(n) {
    in cache -> cache[n]!!
    1L -> 1L
    else -> { 
      val output = 1L + compute(next(n))
      cache[n] = output
      output
    }
  }

  fun next(n: Long) = when {
    n % 2L == 0L -> n / 2L
    else -> 3L * n + 1L
  }
}

println((1L..1000000L).asSequence().maxByOrNull(CollatzLength::compute))
