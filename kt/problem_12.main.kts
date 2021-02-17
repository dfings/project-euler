#!/usr/bin/env kotlin

@file:Import("factor.main.kts")

fun triangularNumbers(): Sequence<Long> = generateSequence(1L, Long::inc).map { (1..it).sum() }

println(triangularNumbers().dropWhile { factors(it).count() <= 500 }.first())
