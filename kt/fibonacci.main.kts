fun fibonacci(): Sequence<Long> = generateSequence(
  Pair(0L, 1L),
  { Pair(it.second, it.first + it.second) }
).map { it.first }
