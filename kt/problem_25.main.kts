#!/usr/bin/env kotlin

@file:Import("factorial.main.kts")
@file:Import("fibonacci.main.kts")

val LIMIT = 10.toBigInteger().pow(999)
println(1 + bigFibonacci().takeWhile { it < LIMIT }.map { 1 }.sum())
