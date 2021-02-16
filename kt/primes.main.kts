fun primes(): Sequence<Long> = sequence {
  val knownPrimeFactors = hashMapOf<Long, HashSet<Long>>()
  fun addKnownPrimeFactor(composite: Long, prime: Long) {
    knownPrimeFactors.getOrPut(composite) { hashSetOf<Long>() }.add(prime)
  }
  for (current in generateSequence(2L, Long::inc)) {
    val primeFactors = knownPrimeFactors[current]
    if (primeFactors == null) {
      yield(current)
      addKnownPrimeFactor(current * current, current)
    } else {
      primeFactors.forEach { addKnownPrimeFactor(current + it, it) }
      knownPrimeFactors.remove(current)  // Reclaim memory
    }    
  }
}
