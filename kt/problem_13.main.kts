#!/usr/bin/env kotlin

import java.io.File
import java.math.BigInteger

fun Iterable<BigInteger>.sum() = reduce { acc, it -> acc.add(it) }

println(File(args[0]).readLines().map { it.toBigInteger() }.sum().toString().take(10))
