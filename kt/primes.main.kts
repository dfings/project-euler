fun Primes(): Sequence<Long> = sequence {
  var i: Long = 2L
  var sieve = hashMapOf<Long, MutableList<Long>>()

  while (true) {
    val result = sieve[i]
    if (result == null) {
      yield(i)
      sieve[i * i] = mutableListOf(i)
    } else {
      sieve.remove(i)
      for (j in result) {
        sieve.getOrPut(i + j) { mutableListOf<Long>() }.add(j)
      }
    }
    i += 1
  }
}
