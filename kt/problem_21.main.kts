#!/usr/bin/env kotlin

@file:Import("factor.main.kts")


val cache = hashMapOf<Long, Long>()
fun d(n: Long) = cache.getOrPut(n) {
    return 1L + factors(n).sum()
}


println((1L..9999L).filter { x -> x == d(d(x)) && x != d(x) }.sum())
