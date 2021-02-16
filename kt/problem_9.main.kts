#!/usr/bin/env kotlin

fun Int.squared() = this * this
data class Triangle(val a: Int, val b: Int, val c: Int) {
  fun isRight() = a.squared() + b.squared() == c.squared()
}

val triangle = (1..333).asSequence().flatMap { 
  a -> (a..500).asSequence().map { b -> Triangle(a, b, 1000 - a - b) } 
}.filter(Triangle::isRight).first()

println(triangle)
println(triangle.a * triangle.b * triangle.c)
