#!/usr/bin/env kotlin

@file:Import("factor.main.kts")

println(primeFactors(600851475143).last())
println(primeFactorsTailRec(600851475143).last())
println(primeFactorsLazy(600851475143).last())
