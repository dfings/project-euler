#!/usr/bin/env kotlin

@file:Import("product.main.kts")

fun productsOfThreeDigitNumbers() = 
  (100..999 cartesianProduct 100..999).map { it.first * it.second }

fun isPalindrome(value: Int) = value.toString().let { it == it.reversed() }

println(productsOfThreeDigitNumbers().filter(::isPalindrome).maxOrNull())
