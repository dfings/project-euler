#!/usr/bin/env kotlin

@file:Import("factorial.main.kts")

val cache = hashMapOf<Pair<Int, Int>, Long>()
fun countPaths(x: Int, y: Int): Long = cache.getOrPut(x to y) {
    when {
        x < 0 || y < 0 -> 0L
        x == 0 && y == 0 -> 1L
        else -> countPaths(x - 1, y) + countPaths(x, y - 1)
    }
}

println(countPaths(20, 20))
println(factorial(40) / (factorial(20) * factorial(20)))