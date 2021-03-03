Iterable<int> range(int a, int b) sync* {
  for (int i = a; i < b; i++) {
    yield i;
  }
}

int sum(Iterable<int> iter) {
  return iter.reduce((value, element) => value + element);
}
