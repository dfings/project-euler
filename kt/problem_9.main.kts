#!/usr/bin/env kotlin

@file:Import("product.main.kts")

fun Int.squared() = this * this
data class Triangle(val a: Int, val b: Int, val c: Int) {
  fun isRight() = a.squared() + b.squared() == c.squared()
}

val triangle = cartesianProduct(1..333, 1..500).map { 
  Triangle(it.first, it.second, 1000 - it.first - it.second)
}.filter(Triangle::isRight).first()
println(triangle)
println(triangle.a * triangle.b * triangle.c)
