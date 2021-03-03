Iterable<int> fibonacci() sync* {
  int a = 1, b = 1;
  while (true) {
    yield a;
    b = a + b;
    a = b - a;
  }
}
