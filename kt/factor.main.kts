fun primeFactors(value: Long): Sequence<Long> {
  fun recur(n: Long, i: Long): Sequence<Long> = when {
    n == 1L -> sequenceOf<Long>()
    n % i == 0L -> sequence { yield(i); yieldAll(recur(n / i, i)) }
    else -> recur(n, i + 1)
  }
  return recur(value, 2)
}

fun factors(value: Long): Sequence<Long> {
  fun recur(n: Long, i: Long): Sequence<Long> = when {
    n < i * i -> sequenceOf<Long>()
    n == i * i -> sequenceOf(i)
    n % i == 0L -> sequence { yield(i); yield(n/i); yieldAll(recur(n, i + 1)) }
    else -> recur(n, i + 1)
  }
  return recur(value, 2)
}
