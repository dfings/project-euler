#!/usr/bin/env kotlin

@file:Import("fibonacci.main.kts")

println(fibonacci().takeWhile { it < 4000000 }.filter { it % 2 == 0L }.sum())
