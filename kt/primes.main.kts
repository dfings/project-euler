import com.google.common.collect.ArrayListMultimap

fun primes(): Sequence<Long> = sequence {
  var current: Long = 2L
  var knownPrimeFactors = ArrayListMultimap.create<Long, Long>()

  while (true) {
    val primeFactors = knownPrimeFactors[current]
    if (primeFactors.isEmpty()) {
      yield(current)
      knownPrimeFactors.put(current * current, current)
    } else {
      knownPrimeFactors.removeAll(current)  // Reclaim memory
      for (primeFactor in primeFactors) {
        knownPrimeFactors.put(current + primeFactor, primeFactor)
      }
    }    
    current += 1
  }
}
