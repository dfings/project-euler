fun Fibonacci(): Sequence<Long> = sequence {
  var terms = Pair(0L, 1L)
  while (true) {
    yield(terms.first)
    terms = Pair(terms.second, terms.first + terms.second)
  }
}
