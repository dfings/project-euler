List<int> primeFactors(int n) {
  var factors = new List<int>();
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
