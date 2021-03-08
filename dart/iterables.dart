import 'dart:math' as math;

Iterable<T> mapProduct<R, S, T>(
    Iterable<R> first, Iterable<S> second, T Function(R, S) f) {
  return first.expand((a) => second.map((b) => f(a, b)));
}

Iterable<int> range(int a, int b) sync* {
  for (int i = a; i < b; i++) {
    yield i;
  }
}

extension NumIterables<T extends num> on Iterable<T> {
  T max() => reduce(math.max);
}

extension IntIterables on Iterable<int> {
  int sum() => isEmpty ? 0 : reduce((value, element) => value + element);
}
