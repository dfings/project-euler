@file:Import("primes.main.kts")

fun PrimeFactors(value: Long): Sequence<Long> = sequence {
  var n = value
  for (prime in Primes()) {
    if (n % prime == 0L) {
      yield(n)
      while (n % prime == 0L) n /= prime
    }
    if (n == 1L) break
  }
}
