#!/usr/bin/env dart

import 'iterables.dart';

void main() {
  print(sum(range(1, 1000).where((i) => i % 3 == 0 || i % 5 == 0)));
}
