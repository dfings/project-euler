#!/usr/bin/env dart

import 'iterables.dart';

int gcd(int x, int y) => y == 0 ? x : gcd(y, x % y);
int lcm(int x, int y) => (x * y) ~/ gcd(x, y);

void main() {
  print(range(1, 20).reduce(lcm));
}
