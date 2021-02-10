fun Fibonacci() = sequence {
  var terms = Pair(0, 1)
  while (true) {
    yield(terms.first)
    terms = Pair(terms.second, terms.first + terms.second)
  }
}
