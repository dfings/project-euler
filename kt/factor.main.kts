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
