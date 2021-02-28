#!/usr/bin/env kotlin

@file:Import("strings.main.kts")

import java.math.BigInteger

println((1..100).map { it.toBigInteger() }.reduce(BigInteger::times).toString().sumOfDigits())
