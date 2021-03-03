Iterable<int> range(int a, int b) sync* {
  for (int i = a; i < b; i++) {
    yield i;
  }
}

extension NumIterables<T extends num> on Iterable<T> {
  T sum() {
    return this.reduce((value, element) => value + element);
  }
}
