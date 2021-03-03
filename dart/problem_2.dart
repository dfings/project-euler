#!/usr/bin/env dart

import 'fibonacci.dart';
import 'iterables.dart';

bool even(int n) {
  return n % 2 == 0;
}

void main() {
  print(fibonacci().takeWhile((i) => i < 4000000).where(even).sum());
}
