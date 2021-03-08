#!/usr/bin/env dart

import 'iterables.dart';
import 'strings.dart';

Iterable<int> productsOfThreeDigitNumbers() {
  return mapProduct(
      range(100, 1000), range(100, 1000), (int i, int j) => i * j);
}

bool isPalindrome(int n) {
  var s = n.toString();
  return s == s.reversed;
}

void main() {
  print(productsOfThreeDigitNumbers().where(isPalindrome).max());
}
