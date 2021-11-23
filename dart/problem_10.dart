#!/usr/bin/env dart

import 'primes.dart';
import 'package:collection/collection.dart';

void main() {
  print(primes().takeWhile((p) => p < 2000000).sum);
}
