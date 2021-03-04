#!/usr/bin/env dart

import 'iterables.dart';

int square(int x) => x * x;
int squareOfSum(int x) => square(range(1, x).sum());
int sumOfSquares(int x) => range(1, x).map(square).sum();

void main() {
  print(squareOfSum(100) - sumOfSquares(100));
}
