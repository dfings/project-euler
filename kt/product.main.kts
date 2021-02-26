infix fun <T, U> Sequence<T>.cartesianProduct(other: Sequence<U>): Sequence<Pair<T, U>> =
  this.flatMap { i -> other.map { j -> i to j } }
  
infix fun <T, U> Iterable<T>.cartesianProduct(other: Iterable<U>): Sequence<Pair<T, U>> =
  this.asSequence() cartesianProduct other.asSequence()
