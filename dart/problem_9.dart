#!/usr/bin/env dart

import 'iterables.dart';

List<int> find_right_triangle() {
  return range(1, 333)
      .expand((a) => range(a, 500).map((b) => [a, b, 1000 - a - b]))
      .where((t) => t[0] * t[0] + t[1] * t[1] == t[2] * t[2])
      .first;
}

void main() {
  var triangle = find_right_triangle();
  print(triangle);
  print(triangle.product());
}
