#!/usr/bin/env kotlin

@file:Import("primes.main.kts")

println(primes().drop(9999).first())
