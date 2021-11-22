#!/usr/bin/env dart

import 'iterables.dart';

List<int> find_right_triangle() {
  for (int a = 1; a <= 333; a++) {
    for (int b = a; b <= 500; b++) {
      int c = 1000 - a - b;
      if (a * a + b * b == c * c) {
        return [a, b, c];
      }
    }
  }
  throw Exception("Didn't find value.");
}

void main() {
  var triangle = find_right_triangle();
  print(triangle);
  print(triangle.product());
}
