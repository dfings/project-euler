import 'dart:collection';

Iterable<int> primes() sync* {
  var sieve = HashMap<int, List<int>>();
  for (var i = 2; true; i++) {
    var res = sieve[i];
    if (res == null) {
      yield i;
      sieve[i * i] = [i];
    } else {
      sieve.remove(i);
      for (var j in res) {
        sieve.putIfAbsent(i + j, () => []).add(j);
      }
    }
  }
}
