#!/usr/bin/env kotlin

// @file:DependsOn("com.google.guava:guava:30.1-jre")

@file:Import("factor.main.kts")

println(primeFactors(600851475143).last())
println(primeFactorsTailRec(600851475143).last())
println(primeFactorsLazy(600851475143).last())
