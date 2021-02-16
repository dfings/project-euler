#!/usr/bin/env kotlin

@file:Import("primes.main.kts")

println(primes().takeWhile { it < 2000000 }.sum())
