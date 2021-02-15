#!/usr/bin/env kotlin

fun square(x: Int) = x * x
fun squareOfSum(x: Int): Int = square((1..x).sum())
fun sumOfSquares(x: Int): Int = (1..x).map(::square).sum()

println(squareOfSum(100) - sumOfSquares(100))
