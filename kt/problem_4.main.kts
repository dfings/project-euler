#!/usr/bin/env kotlin

fun isPalindrome(value: Int) = value.toString() == value.toString().reversed()

println(sequence {
  for (x in 100..999) {
    for (y in 100..999) {
      yield(x * y)
    }
  }
}.filter(::isPalindrome).maxOrNull())
