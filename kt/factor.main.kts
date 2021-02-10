@file:Import("primes.main.kts")

fun PrimeFactors(value: Long): List<Long> {
  var n = value
  val factors = mutableListOf<Long>()
  for (prime in Primes()) {
    if (n % prime == 0L) {
      factors.add(n)
      while (n % prime == 0L) n /= prime
    }
    if (n == 1L) {
      return factors
    }
  }
  throw AssertionError()
}
