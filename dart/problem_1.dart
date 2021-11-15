#!/usr/bin/env dart

import 'iterables.dart';
import 'package:collection/collection.dart';

void main() {
  print(range(1, 1000).where((i) => i % 3 == 0 || i % 5 == 0).sum);
}
