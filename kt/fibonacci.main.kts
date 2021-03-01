import java.math.BigInteger

fun fibonacci(): Sequence<Long> = generateSequence(
  1L to 1L,
  { it.second to it.first + it.second }
).map { it.first }

fun bigFibonacci(): Sequence<BigInteger> = generateSequence(
  1.toBigInteger() to 1.toBigInteger(),
  { it.second to it.first + it.second }
).map { it.first }
