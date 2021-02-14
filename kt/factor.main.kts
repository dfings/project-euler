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


data class FactorState(val n: Long, val i: Long, val out: List<Long>) {
  fun next() = if (n % i == 0L) hit() else miss()
  fun miss() = FactorState(n, i + 1, out)
  fun hit() = FactorState(n / i, i, out + i)
}

tailrec fun primeFactorsStateMachine(state: FactorState): FactorState = 
  when (state.n) {
    1L -> state
    else -> primeFactorsStateMachine(state.next())
  }

fun primeFactorsTailRec(value: Long): List<Long> =
  primeFactorsStateMachine(FactorState(value, 2, listOf<Long>())).out


// Note converting Sequence to List results in StackOverflowError
fun primeFactorsLazyWith(n: Long, i: Long): Sequence<Long> = when {
  n == 1L -> sequenceOf<Long>()
  n % i == 0L -> sequenceOf(i) + primeFactorsLazyWith(n / i, i)
  else -> primeFactorsLazyWith(n, i + 1)
}

fun primeFactorsLazy(value: Long): Sequence<Long> =
  primeFactorsLazyWith(value, 2)
