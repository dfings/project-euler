fun primeFactors(value: Long): Sequence<Long> = sequence {
  var n = value
  var i = 2L
  while (n > 1) {
    if (n % i == 0L) {
      yield(i)
      n /= i
    } else {
      i++
    }
  }
}


private data class FactorState(val n: Long, val i: Long, val out: List<Long>) {
  fun done() = n == 1L
  fun next() = if (n % i == 0L) hit() else miss()
  fun miss() = FactorState(n, i + 1, out)
  fun hit() = FactorState(n / i, i, out + i)
}

fun primeFactorsTailRec(value: Long): List<Long> {
  tailrec fun recur(state: FactorState): FactorState = 
    if (state.done()) state else recur(state.next())
  return recur(FactorState(value, 2, listOf<Long>())).out
}


fun primeFactorsLazy(value: Long): Sequence<Long> {
  fun recur(n: Long, i: Long): Sequence<Long> = when {
    n == 1L -> sequenceOf<Long>()
    n % i == 0L -> sequence { yield(i); yieldAll(recur(n / i, i)) }
    else -> recur(n, i + 1)
  }
  return recur(value, 2)
}
