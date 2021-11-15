#!/usr/bin/env dart

import 'dart:collection';
import 'factor.dart';
import 'iterables.dart';
import 'package:collection/collection.dart';

var cache = HashMap<int, int>();
int d(int n) => cache.putIfAbsent(n, () => 1 + factor(n).sum);

void main() {
  print(range(1, 9999).where((x) => x == d(d(x)) && x != d(x)).sum);
}
