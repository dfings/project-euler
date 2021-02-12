fun fibonacci(): Sequence<Long> = sequence {
  var state = Pair(0L, 1L) // current, next
  while (true) {
    yield(state.first)
    state = Pair(state.second, state.first + state.second)
  }
}
