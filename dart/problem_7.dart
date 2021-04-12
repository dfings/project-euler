#!/usr/bin/env dart

import 'primes.dart';

void main() {
  print(primes().skip(10000).first);
}
