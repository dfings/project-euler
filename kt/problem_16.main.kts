#!/usr/bin/env kotlin

@file:Import("strings.main.kts")

println(2.toBigInteger().pow(1000).toString().sumOfDigits())
