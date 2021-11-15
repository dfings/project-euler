#!/usr/bin/env dart

import 'fibonacci.dart';
import 'package:collection/collection.dart';

bool even(int n) => n % 2 == 0;

void main() {
  print(fibonacci().takeWhile((i) => i < 4000000).where(even).sum);
}
