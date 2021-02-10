fun <T, U> cartesianProduct(first: Sequence<T>, second: Sequence<U>): Sequence<Pair<T, U>> =
  first.flatMap { i -> second.map { j -> i to j } }
  
fun <T, U> cartesianProduct(first: Iterable<T>, second: Iterable<U>): Sequence<Pair<T, U>> =
  cartesianProduct(first.asSequence(), second.asSequence())
