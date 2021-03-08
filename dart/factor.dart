import 'dart:math';

List<int> primeFactors(int n) {
  List<int> factors = [];
  var i = 2;
  while (n > 1) {
    if (n % i == 0) {
      factors.add(i);
      n ~/= i;
    } else {
      i += 1;
    }
  }
  return factors;
}

List<int> factor(int n) {
  List<int> factors = [];
  var i = 2;
  var limit = sqrt(n);
  while (i < limit) {
    if (n % i == 0) {
      factors.add(i);
      factors.add(n ~/ i);
    }
    i += 1;
  }
  if (i * i == n) {
    factors.add(i);
  }
  return factors;
}
