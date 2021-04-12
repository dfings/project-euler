#!/usr/bin/env kotlin

@file:Import("primes.main.kts")

println(primes().drop(10000).first())
