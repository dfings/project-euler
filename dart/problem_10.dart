#!/usr/bin/env dart

import 'iterables.dart';
import 'primes.dart';

void main() {
  print(primes().takeWhile((p) => p < 2000000).sum());
}
