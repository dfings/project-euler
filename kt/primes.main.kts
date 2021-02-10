fun Primes(): Sequence<Long> = sequence {
  var current: Long = 2L
  var knownPrimeFactors = hashMapOf<Long, MutableList<Long>>()

  while (true) {
    val primeFactors = knownPrimeFactors[current]
    if (primeFactors == null) {
      yield(current)
      knownPrimeFactors[current * current] = mutableListOf(current)
    } else {
      knownPrimeFactors.remove(current)  // Reclaim memory
      for (primeFactor in primeFactors) {
        knownPrimeFactors.getOrPut(current + primeFactor) { 
          mutableListOf<Long>() 
        }.add(primeFactor)
      }
    }    
    current += 1
  }
}
